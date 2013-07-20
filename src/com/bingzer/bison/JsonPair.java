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

import com.bingzer.bison.commons.Constants;
import com.bingzer.bison.commons.JsonUtil;
import com.bingzer.bison.commons.StringUtil;

/**
 * Json pair.
 *
 * @author Ricky Tobing
 */
@ com.bingzer.common.serial.Entity(name="JsonPair", browsable=false)
public class JsonPair extends JsonValue<Object> implements Comparable<JsonPair>{
    
    private CharSequence name;
    
    /**
     * New instance of Json find
     * @param parent 
     */
    protected JsonPair(Json parent){
        super(parent);
    }
    
    /**
     * Returns the name of this Json if any. If this Json does not
     * have name, it should return null
     * 
     * @return name Json's name
     */
    public CharSequence name(){
        return name;
    }
    
    /**
     * Sets/Modifies the name of this Json. Returns itself
     * for code-chaining purposes
     * @param name the new name to set
     * @return this
     */
    public Json name(CharSequence name){
        // trim first..
        name = StringUtil.trim(name);
        
        // trim name if it starts with (') or (")
        if(StringUtil.startsWith(name, Constants.QUOTE) && StringUtil.startsWith(name, Constants.QUOTE))
            name = name.subSequence(1, name.length() - 1);
        else if(StringUtil.startsWith(name, Constants.DOUBLE_QUOTE) && StringUtil.startsWith(name, Constants.DOUBLE_QUOTE))
            name = name.subSequence(1, name.length() - 1);
        
        // validate name..
        if(!JsonUtil.isNameValid(name))
            Bison.syntaxError(this, name);
        this.name = name;
        return this;
    }
    
    /**
     * Equivalent to calling valueAs(any)
     * @param any
     * @return
     * @throws JsonException 
     */
    @Override
    protected Json append(Object any) throws JsonException {
        if(value != null && (value instanceof JsonObject || value instanceof JsonArray))
            ((Json)value).append(any);
        else
            value(Bison.jsonify(Json.class, this, any));
        return this;
    }   

    /**
     * Override valueAs
     * @param value 
     * @return 
     */
    @Override
    public Json value(Object value) {
        if(value instanceof JsonPair){
            value = Bison.jsonify(Json.class, this, Json.OBJECT).append(value);
        }
        return super.value(value);
    }
    
    /**
     * Look for pair's name
     * @param name
     * @return 
     */
    @Override
    public JsonPair find(CharSequence name) {
        if(StringUtil.equals(name(), name))
            return this;
        else if(value != null && 
                !(value instanceof JsonType) && value instanceof Json){
            if(value instanceof JsonPair &&
                    StringUtil.equals(((JsonPair)value).name(), name))
                return (JsonPair)value;
            else{
                return ((Json)value).find(name);
            }
        }
                
        // mock a json pair..
        // TODO:
        return null;//mock(JsonPair.class);
    }

    /**
     * Calling this method will modify current name the current valueAs
     * @param value 
     */
    @Override
    public Json pair(CharSequence name, Object value) throws JsonException {
        Json json = null;
        
        // if parent is a JsonArray
        if(parent() instanceof JsonArray){
            // create 'an empty parent object for this json
            json = new JsonObject(this);
            json.pair(name, Bison.jsonify(Json.class, json, value));
        }
        // otherwise, let's just add this
        else {
            json = new JsonPair(this);
            json.as(JsonPair.class).name(name);
            json.value(Bison.jsonify(Json.class, json, value));
        }
        
        // assign the json object as its 'valueAs'
        append(json);
        
        return this;
    }


    /**
     * Parses jsonText
     * @param jsonText
     * @return
     * @throws JsonException 
     */
    @Override
    protected Json parse(CharSequence jsonText) throws JsonException {
        //<editor-fold defaultstate="collapsed" desc="Parsing code">
        
        // "name":"valueOf"
        // 'name':true
        // name:134
        // name:{}
        
        CharSequence name = new StringBuilder();
        value = new StringBuilder();
        
        char ch;
        boolean quoteCount = false;
        boolean doubleQuoteCount = false;
        int parseToggle = 0; // 0 == name 1 == valueOf
        for(int i = 0; i < jsonText.length(); i++){
            ch = jsonText.charAt(i);
            
            try{
                // -- if quote
                if(ch == Constants.QUOTE){
                    if((i > 1 && jsonText.charAt(i - 1) != Constants.SLASH) || i == 0)
                        quoteCount = !quoteCount;
                }
                else if(ch == Constants.DOUBLE_QUOTE){
                    if(i > 1 && jsonText.charAt(i - 1) != Constants.SLASH || i == 0)
                        doubleQuoteCount = !doubleQuoteCount;
                }
                else if(ch == Constants.COLON){
                // with all these conditions
                    if(!quoteCount && !doubleQuoteCount &&
                       i > 0 && jsonText.charAt(i -1 ) != Constants.SLASH &&
                       parseToggle == 0){
                        parseToggle = 1;
                        // increment by 1
                        // we don't want the stupid colon
                        i++;
                        ch = jsonText.charAt(i);
                    }
                }

                // -- parses according to the toggle
                switch(parseToggle){
                    case 0: ((StringBuilder)name).append(ch); break;
                    case 1: ((StringBuilder)value).append(ch); break;
                }
            }
            catch (StringIndexOutOfBoundsException e){
                // incorrect formatted..
                Bison.syntaxError(this, jsonText);
            }
        }// end for..
        
        // assign name
        name(name);
        // we must append valueOf..
        if(((StringBuilder)value).length() == 0) Bison.syntaxError(this, jsonText);
        value = Bison.jsonify(Json.class, this, (CharSequence)value); 
        
        // gc.
        name = null;
        
        //</editor-fold>
        // return this
        return this;
    }

    /**
     * Returns the correct json rep text
     * @param tabChar 
     * @param lineChar 
     * @return 
     */
    @Override
    public java.lang.String toString(CharSequence tabChar, CharSequence lineChar) {
        StringBuilder builder = new StringBuilder();
        
        int rootCount = tabChar == null && lineChar == null ? 0 : rootCount();
        for(int i = 0; i < rootCount; i++)
            builder.append(tabChar); 
        
        // build the string
        builder.append(Bison.Setting.getNameQuoter()).append(name()).append(Bison.Setting.getNameQuoter())
                .append(Constants.COLON)
                .append(value instanceof JsonValue ? ((JsonValue)value).toString(tabChar, lineChar) : value);
        
        return builder.toString();
    }

    /**
     * Compare to other object.
     * The object must be a Json type otherwise, it should always return -1.
     * @param o other object
     * @return -1 = smaller, 1 = bigger, 0 the same
     */
    @Override
    public int compareTo(JsonPair o) {
        if(o != null && o.name != null && this.name != null)
            return o.name.toString().compareTo(name.toString());
        // the same
        return -1;
    }
    
}
