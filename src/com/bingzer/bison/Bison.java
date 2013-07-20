/**
 * Copyright 2011 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance insert the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.bingzer.bison;

import com.bingzer.bison.commons.Common;
import com.bingzer.bison.commons.Constants;
import com.bingzer.bison.commons.JsonUtil;
import com.bingzer.bison.commons.Options;
import com.bingzer.bison.commons.StringUtil;
import com.bingzer.bison.serializer.Objectifiable;
import com.bingzer.common.OnChangedListener;

/**
 *
 * @author 11856
 */
public class Bison {   
    
    /**
     * Json Object.
     * Equivalent to '{}'
     */
    public static final CharSequence OBJECT = Json.OBJECT;
    
    /**
     * Json Array
     * Equivalent to '[]'
     */
    public static final CharSequence ARRAY = Json.ARRAY;
    
    /**
     * Json Pair
     * Equivalent to ''
     */
    public static final CharSequence PAIR = Json.PAIR;
    
    /**
     * Jsonify <code>jsonText</code> to Json object.
     * Bison API can handle any correctly-formatted type of <code>jsonText</code> 
     * and properly transform <code>jsonText</code> to its Json object representation.
     * This method will assign <code>null</code> to its Json parent. Therefore,
     * every Json object that is created by this method, will be considered as a
     * root object.
     * <p>
     * Equivalent to calling <code>Json.jsonify(null, jsonText)</code>
     * </p>
     * <p>
     *  <strong>Example</strong>
     * </p>
     *  <ul>
     *      <li><code>Json json = Json.jsonify("animal:'bison'");</code><br/>
     * will produce a <code>JsonPair</code> with '<code>animal</code>' as the
     * name and '<code>bison</code>' as the valueAs.</li>
     *      <li><code>Json json = Json.jsonify(Json.OBJECT);</code><br/>
     * will create an empty <code>JsonObject</code> equivalent to 
     * '<code>{}</code>' in JSON</li>
     *      <li><code>Json json = Json.jsonify("[1,3,100,true,'string']");</code><br/>
     * will create <code>JsonArray</code> with specified values</li>
     *  </ul>
     * 
     * 
     * 
     * @param jsonText correctly-formatted jsonText
     * 
     * @return Json object
     * 
     * @throws JsonException If jsonText is in incorrect format
     * 
     * @see Json#jsonify(com.bingzer.bison.Json, java.lang.CharSequence) 
     */
    public static Json jsonify(CharSequence jsonText) throws JsonException{
        return jsonify(null, jsonText);
    }
    
    /**
     * Jsonify <code>jsonText</code> to Json object.
     * Bison API can handle any correctly-formatted type of <code>jsonText</code> 
     * and properly transform <code>jsonText</code> to its Json object representation.
     * This method will assign <code>parent</code> to its Json parent. Therefore,
     * every Json object that is created by this method, will have <code>parent</code>
     * as its parent :)
     * <p>
     * Equivalent to calling <code>Json.jsonify(null, (Object) jsonText)</code>
     * </p>
     * <p>
     * <strong>Example</strong>
     * </p>
     *  <ul>
     *      <li><code>Json json = Json.jsonify(parent, "animal:'bison'");</code><br/>
     * will produce a <code>JsonPair</code> with '<code>animal</code>' as the
     * name and '<code>bison</code>' as the valueAs.</li>
     *      <li><code>Json json = Json.jsonify(parent, Json.OBJECT);</code><br/>
     * will create an empty <code>JsonObject</code> equivalent to 
     * '<code>{}</code>' in JSON</li>
     *      <li><code>Json json = Json.jsonify(parent, "[1,3,100,true,'string']");</code><br/>
     * will create <code>JsonArray</code> with specified values</li>
     *  </ul>
     * 
     * 
     * 
     * @param jsonText correctly-formatted jsonText
     * @param parent This Json's parent (type of <code>Json</code>). May be null
     * 
     * @return Json object
     * 
     * @throws JsonException If jsonText is in incorrect format
     * 
     * @see Json#jsonify(com.bingzer.bison.Json, java.lang.Object) 
     */
    public static Json jsonify(Json parent, CharSequence jsonText) throws JsonException{
        return jsonify(parent, (Object) jsonText);
    }
    
    /**
     * Creates the correct representation of Json object using <code>any</code>.
     * This method will 'serialize' <code>any</code> to its correct representation
     * of its Json counterpart.
     * 
     * @param any Any java object.
     * 
     * @return The Json representation
     * 
     * @throws JsonException 
     */
    public static Json jsonify(Object any) throws JsonException{
        return jsonify(null, any);
    }
    
    /**
     * Creates the correct representation of Json object using <code>any</code>.
     * This method will 'serialize' <code>any</code> to its correct representation
     * of its Json counterpart.
     * 
     * @param parent 
     * @param any 
     * @return The Json representation
     * 
     * @throws JsonException 
     */
    public static Json jsonify(Json parent, Object any) throws JsonException{
        return jsonify(Json.class, parent, any);
    }
    
    /**
     * Internal use only.
     * <p>
     * Creates Json using jsonText
     * This is an internal method used widely throughout this API.
     * Outsider code should never use this method
     * 
     * @param <T>
     * @param clazz
     * @param parent
     * @param any
     * @return
     * @throws JsonException 
     */
    @SuppressWarnings("unchecked")
    protected static <T extends Json> T jsonify(Class<T> clazz, Json parent, Object any) throws JsonException{
        T json = null;
        CharSequence jsonText = null;
        
        if(any == null)
            json = (T) new JsonNull(parent); // no need to parse
        else if(any instanceof Character){
            // build character
            jsonText = new StringBuilder().append(Constants.DOUBLE_QUOTE).append(any).append(Constants.DOUBLE_QUOTE);
            json = (T) new JsonString(parent).parse(jsonText); 
        }
        // ---- boolean
        else if(any instanceof Boolean)
            json = (T) new JsonBoolean(parent).parse(any.toString());
        // ---- Number
        else if(any instanceof Number)
            json = (T) new JsonNumber(parent).parse(any.toString());
        // ---- Date
        else if(any instanceof java.util.Date){
            // build json date
            // ex: new Date(valueAs) where valueAs is 'long' valueAs
            jsonText = new StringBuilder(Constants.NEW).append(Constants.SPACE).append(Constants.DATE)
                        .append(Constants.PARENTHESE_START)
                        .append(((Long)((java.util.Date)any).getTime()).toString())
                        .append(Constants.PARENTHESE_END);
            json = (T) new JsonDate(parent).parse(jsonText);
        }
        // ---- Other json object
        else if(any instanceof Json){
            json = (T)(Json)any;
            // switch parent
            //json.parent = parent;
        }
        // ---- Array
        else if(Common.isObjectArray(any)){
            Object[] array = Common.toObjectArray(any);
            
            json = (T) new JsonArray(parent);
            // build the json array
            jsonText = new StringBuilder().append(Constants.ARRAY_START);
            for(int i = 0; i < array.length; i++){
                Json child = jsonify(json, array[i]);
                ((StringBuilder)jsonText).append(child);
                // append comma
                if(i != array.length - 1)
                    ((StringBuilder)jsonText).append(Constants.COMMA);
            }
            ((StringBuilder)jsonText).append(Constants.ARRAY_END);
            
            json.as(JsonValue.class).parse(jsonText);
        }
        // --- Java enum.. 
        else if(any.getClass().isEnum()){
            // conver this to string..
            any = new StringBuilder().append(Constants.DOUBLE_QUOTE).append(any).append(Constants.DOUBLE_QUOTE);
            json = (T) new JsonString(parent).parse((CharSequence)any);
        }
        // --- char sequence
        else if(any instanceof CharSequence){
            jsonText = StringUtil.trim((CharSequence)any);
            // see if it starts with \" or just '
            if(JsonUtil.isString(jsonText))              json = (T) new JsonString(parent).parse(jsonText);            
            else if(StringUtil.equals(OBJECT, jsonText)) json = (T) new JsonObject(parent);
            else if(StringUtil.equals(ARRAY, jsonText))  json = (T) new JsonArray(parent);
            else if(StringUtil.equals(PAIR, jsonText))   json = (T) new JsonPair(parent);
            else {
                // string..
                if(jsonText.length() > 0){
                    if(JsonUtil.isString(jsonText))
                        json = (T) new JsonString(parent);
                    else if(JsonUtil.isJsonObject(jsonText))
                        json = (T) new JsonObject(parent);
                    else if(JsonUtil.isJsonArray(jsonText))
                        json = (T) new JsonArray(parent);
                    else if(JsonUtil.isBoolean(jsonText))
                        json = (T) new JsonBoolean(parent);
                    else if(JsonUtil.isNull(jsonText))
                        json = (T) new JsonNull(parent);
                    else if(JsonUtil.isNumber(jsonText))
                        json = (T) new JsonNumber(parent);
                    else if(JsonUtil.isDate(jsonText))
                        json = (T) new JsonDate(parent);
                    else
                        // -- anything else
                        json = (T) new JsonPair(parent);
                }
                // -- totally empty space
                //else throw new JsonException("Value is empty");
                // append..
                json.as(JsonValue.class).parse(jsonText);
            }
        
        }// end if charsequence
        // ---- Other java object.. mm.. what to do..
        else{
            // must distinguish between creating a find or object..           
            // TODO:
            json = (T) new JsonObject(parent);
            
            if(any.getClass().getAnnotation(com.bingzer.common.serial.Entity.class) != null){
                json.as(JsonValue.class)
                        .setBrowsable(any.getClass().getAnnotation(com.bingzer.common.serial.Entity.class).browsable());
            }
            
            if(json.as(JsonValue.class).isBrowsable()){
                json = (T) Setting.getObjectifier().toJson(any, json);
            }
        }
        
        return json;
    }
    
    /**
     * Stringify
     * @param any
     * @return
     * @throws JsonException 
     */
    public static CharSequence stringify(Object any) throws JsonException{
        return stringify(any, null, null);
    }
    
    /**
     * Stringify
     * @param any
     * @param tabChar
     * @return
     * @throws JsonException 
     */
    public static CharSequence stringify(Object any, CharSequence tabChar) throws JsonException{
        return stringify(any, tabChar, Setting.getLineChar());
    }
    
    /**
     * Stringify
     * @param any
     * @param tabChar
     * @param lineChar
     * @return
     * @throws JsonException 
     */
    public static CharSequence stringify(Object any, CharSequence tabChar, CharSequence lineChar) throws JsonException{
        return jsonify(any).toString(tabChar, lineChar);
    }
    
    /**
     * Objectify a new instance of <code>Class any</code> from a <code>jsonText</code>.
     * The object return will be a new instance of that <code>Class</code>.
     * This method is equivalent of calling:
     * <code>
     * Bison.objectify((Class<T>)any, json.toString())</code>
     * </code>
     * 
     * @param <T> any type
     * @param any any class
     * @param jsonText Json from which <code>type</code> will be serialized from
     * @return a new instance of <code>Class any</code>
     * @throws JsonException unchecked JsonException
     */
    @SuppressWarnings("unchecked")
    public static <T> T objectify(Class<T> any, CharSequence jsonText) throws JsonException{
        return (T) objectify((Object) any, jsonify(jsonText));
    }
    
    /**
     * Objectify a new instance of <code>Class any</code> from a <code>Json</code>.
     * The object return will be a new instance of that <code>Class</code>
     * 
     * @param <T> any type
     * @param any any class
     * @param json Json from which <code>type</code> will be serialized from
     * @return a new instance of <code>Class any</code>
     * @throws JsonException unchecked JsonException
     */
    @SuppressWarnings("unchecked")
    public static <T> T objectify(Class<T> any, Json json) throws JsonException{
        return (T) objectify((Object) any, json);
    }
    
    /**
     * Objectify <code>any</code> from a <code>Json</code>.
     * The return object will be <code>any</code> after being serialized
     * 
     * @param <T> any type
     * @param any any object
     * @param jsonText the Json text
     * @return any
     * @throws JsonException unchecked JsonException
     */
    @SuppressWarnings("unchecked")
    public static <T> T objectify(T any, CharSequence jsonText) throws JsonException{
        return (T) objectify((Object) any, jsonify(jsonText));
    }
    
    /**
     * Objectify <code>any</code> from a <code>Json</code>.
     * The return object will be <code>any</code> after being serialized
     * 
     * @param <T> any type
     * @param any any object
     * @param json the Json text
     * @return any
     * @throws JsonException unchecked JsonException
     */
    @SuppressWarnings("unchecked")
    public static <T> T objectify(T any, Json json) throws JsonException{
        T object = null;
        if(any instanceof Class){
            try{
                any = ((Class<T>)any).newInstance();
            }
            catch (Exception e){
                throw new JsonException(e);
            }
        }
        
        object = Setting.getObjectifier().toObject(json, any);
        
        // return
        return object;
    }
    
    /**
     * Throws syntax error
     * @param json
     * @param jsonText 
     */
    protected static void syntaxError(Json json, CharSequence jsonText){
        String text = "<unknown>";
        if(json != null){
            if(json.parent() != null)
                text = json.parent().toString();
            else text = json.toString();
        }
        String message = "Syntax Error\n\t" + text + " at " + jsonText;
        throw new JsonException(message);
    }
    
    /**
     * Singular setting
     */
    protected static final OptAccess Setting = new OptAccess();
    
    /**
     * Protected static class that provides faster 'getter' access
     * to get valueAs from options
     */
    protected static class OptAccess implements OnChangedListener<String, Object>{
        //<editor-fold defaultstate="collapsed" desc="Private Data Members">
        private Objectifiable objectifier;
        private CharSequence tabChar;
        private CharSequence lineChar;
        private CharSequence nameQuoter;
        private CharSequence stringQuoter;
        private boolean allowsInfinity;
        private boolean allowsNaN;
        //</editor-fold>
        
        /**
         * New instance
         */
        private OptAccess(){
            try{
                // load from Options
                objectifier = (Objectifiable)
                        Class.forName(Options.Global.getOption(Options.SERIALIZER, "com.bingzer.bison.serializer.Objectifier")).newInstance();
                tabChar = "" + Options.Global.getOption(Options.TAB_CHAR, Constants.TAB);
                lineChar = "" + Options.Global.getOption(Options.NEW_LINE_CHAR, Constants.NEW_LINE);
                allowsInfinity = Options.Global.getOption(Options.ALLOWS_INFINITY, true);
                allowsNaN = Options.Global.getOption(Options.ALLOWS_NAN, true);     
                nameQuoter = "" + Options.Global.getOption(Options.DEFAULT_NAME_QUOTER, Constants.DOUBLE_QUOTE);
                stringQuoter = "" + Options.Global.getOption(Options.STRING_QUOTER, Constants.DOUBLE_QUOTE);
            }
            catch (Exception e){
                throw new JsonException("Failed to initialize Global options", e);
            }
        }

        /**
         * Invoked when changed.
         * The <code>Options</code> has 100% assurance that <code>newValue</code>
         * that the new valueAs is ALWAYS the same type as the oldValue
         * @param name
         * @param newValue
         * @param oldValue 
         */
        @Override
        public void onChanged(String name, Object newValue, Object oldValue) {
            if(name.equals(Options.SERIALIZER))
                objectifier = (Objectifiable) newValue;
            else if(name.equals(Options.TAB_CHAR))
                tabChar = "" + newValue;
            else if(name.equals(Options.NEW_LINE_CHAR))
                lineChar = "" + newValue;
            else if(name.equals(Options.ALLOWS_INFINITY))
                allowsInfinity = (Boolean) newValue;
            else if(name.equals(Options.ALLOWS_NAN))
                allowsNaN = (Boolean) newValue;
            else if(name.equals(Options.STRING_QUOTER))
                stringQuoter = newValue + "";
            else if(name.equals(Options.DEFAULT_NAME_QUOTER))
                nameQuoter = newValue + "";
            else {
                // ignore...
            }
        }// end onChanged()
        
        //<editor-fold defaultstate="collapsed" desc="Getters">

        /**
         * 
         * @return
         */
        protected boolean isAllowsInfinity() {
            return allowsInfinity;
        }

        /**
         * 
         * @return
         */
        protected boolean isAllowsNaN() {
            return allowsNaN;
        }

        /**
         * 
         * @return
         */
        protected CharSequence getLineChar() {
            return lineChar;
        }

        /**
         * 
         * @return
         */
        protected CharSequence getTabChar() {
            return tabChar;
        }

        /**
         * 
         * @return
         */
        protected Objectifiable getObjectifier() {
            return objectifier;
        }

        /**
         * 
         * @return
         */
        protected CharSequence getNameQuoter() {
            return nameQuoter;
        }

        /**
         * 
         * @return
         */
        protected CharSequence getStringQuoter() {
            return stringQuoter;
        }
        
        //</editor-fold>
    }
            
    /**
     * Invoke when first called..
     */
    static{
        Options.Global.addListener(Setting);
    }
}
