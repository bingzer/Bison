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
import com.bingzer.bison.commons.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * JsonArray is a type of <code>Collection</code> which is backed
 * by an ArrayList with Json type.
 *
 * @author Ricky Tobing
 */
@   com.bingzer.common.serial.Entity(name="JsonArray", browsable=false)
public class JsonArray extends JsonValue<ArrayList<Json>> implements Collection<Json>{
    
    /**
     * New instance of JsonArray
     * @param json 
     */
    protected JsonArray(Json json){
        super(json);
        value = new ArrayList<Json>();
    }

    /**
     * Append any object to this array
     * @param any 
     * @return 
     * @throws JsonException 
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Json append(Object any) throws JsonException {
        Json json = Bison.jsonify(Json.class, this, any);
        
        // but wait, we can't really append json find to an array
        // we must have a parent object that will hold that find
        if(json instanceof JsonPair){
            // creates an object parent first..
            // this will return the json object
            json = new JsonObject(this).append(json).parent();
        }
        
        // add json
        value.add(json);
        
        // return me
        return this;
    }
    
    /**
     * Finds a pair if any by its name
     * @param name
     * @return 
     */
    @Override
    @SuppressWarnings("unchecked")
    public JsonPair find(CharSequence name) {
        JsonPair p = null;
        for(int i = 0; i < size(); i++){
            if(p == null){
                p = value.get(i).find(name);
                if(p instanceof JsonPair && StringUtil.equals(p.as(JsonPair.class).name(), name)){
                    break;
                }
                else p = null; // reset..
            }
        }
        
        return p;
    }
    
    /**
     * Search for the index in the array for the specific value
     * @param any 
     * @return 
     */
    public int valueIndexOf(Object any){
        any = Bison.jsonify(Json.class, this, any);
        for(int i = 0; i < value.size(); i++){
            if(value.get(i).equals(any)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Replaces 'oldValue' with new valueAs.
     * If oldValue is not found, it will get ignore.
     * Returns this
     * @param oldValue
     * @param newValue
     * @return 
     */
    @SuppressWarnings("unchecked")
    public Json valueReplace(Object oldValue, Object newValue) {
        // make sure we 'jsonify' the old value (input from user)
        oldValue = Bison.jsonify(Json.class, this, oldValue);
        valueAt(valueIndexOf(oldValue), newValue);
        
        return this;
    }
    
    /**
     * Returns the value at <code>index</code>
     * @param index
     * @return 
     */
    @SuppressWarnings("unchecked")
    public Json valueAt(int index){
        try{
            if(index != -1) return value.get(index);
        }
        catch (IndexOutOfBoundsException e){
            throw new JsonException(e);
        }
        
        // not found
        return null;
    }

    /**
     * Sets valueAs valueAt <code>index</code>
     * @param index
     * @param any 
     * @return 
     */
    @SuppressWarnings("unchecked")
    public Json valueAt(int index, Object any){
        try{
            any = Bison.jsonify(Json.class, this, any);
            value.set(index, (JsonValue)any);
        }
        catch (Exception e){
            throw new JsonException(e);
        }
        return this;
    }
    

    /**
     * This method will do nothing. It has been deprecated.
     * To clear use <code>clear()</code>. To append more stuff use
     * <code>append()</code>
     * @param any
     * @return
     * @deprecated
     */
    @Override
    @Deprecated
    public Json value(Object any) {
        //return super.value(any);
        return this;
    }


    /**
     * Creates a object then find, and add them to array
     * @param name
     * @return
     * @throws JsonException 
     */
    @Override
    @SuppressWarnings("unchecked")
    public Json pair(CharSequence name, Object any) throws JsonException {
        Json json = Bison.jsonify(Json.class, this, Json.OBJECT);
        json.pair(name, any);
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
    @SuppressWarnings("unchecked")
    protected JsonValue parse(CharSequence jsonText) throws JsonException{
        jsonText = StringUtil.trim(jsonText);
        
        // must start with { and end with }
        if(!StringUtil.startsWith(jsonText, Constants.ARRAY_START) && !StringUtil.endsWith(jsonText, Constants.ARRAY_END))
            Bison.syntaxError(this, jsonText);
        
        // clear the valueAt
        value.clear();
        
        boolean insideString = false;
        char c;
        
        // split by comma..
        List<CharSequence> values = new LinkedList<CharSequence>(); 
        CharSequence parsedText = new StringBuilder();
        boolean doneParsing = false;
        int startCounter = 0;
        int arrayCounter = 0;
        // we're parsing inside [ & ] not including those brackets
        for(int i = 1; i < jsonText.length(); i++){
            c = jsonText.charAt(i);
            
            if((c == Constants.QUOTE || c == Constants.DOUBLE_QUOTE) && jsonText.charAt(i - 1) != Constants.SLASH) insideString = !insideString;
            else if(!insideString && c == Constants.START) startCounter++;
            else if(!insideString && c == Constants.END) startCounter--;
            else if(!insideString && c == Constants.ARRAY_START)  arrayCounter++;
            else if(!insideString && c == Constants.ARRAY_END && (i != jsonText.length() - 1)) arrayCounter--;
            
            // if it's not inside string, object or other array
            if(!insideString && startCounter == 0 && arrayCounter == 0){
                if(c == Constants.COMMA || i == jsonText.length() - 1){
                    doneParsing = true;
                }
            }
            if(!doneParsing)
                ((StringBuilder)parsedText).append(c);
            
            // if done parsing...
            if(doneParsing){
                // add parsedText..
                values.add(new StringBuilder(parsedText));
                // empty parsedText
                ((StringBuilder)parsedText).delete(0, parsedText.length());
                doneParsing = false;
            }
        }// end for..
        
        //if(!valid)
            //syntaxError(this, jsonText);
        if(values.isEmpty())
            Bison.syntaxError(this, jsonText);
        
        // now.. iterate all values..
        for(CharSequence text : values){
            if(text.length() > 0){
                JsonValue val = Bison.jsonify(JsonValue.class, this, text);
                value.add(val);  
            }
        }
        
        // -- gc..
        values = null;
        parsedText = null;
        
        return this;
    }

    /**
     * To string..
     * @param tabChar
     * @param lineChar
     * @return 
     */
    @Override
    @SuppressWarnings("unchecked")
    public String toString(CharSequence tabChar, CharSequence lineChar) {
        StringBuilder builder = new StringBuilder();
        
        //<editor-fold defaultstate="collapsed" desc="Code">
        int rootCount = tabChar == null && lineChar == null ? 0 : rootCount();
        
        // empty both if null so we don't get 'null' string
        if(lineChar == null) lineChar = Constants.EMPTY; // empty out
        if(tabChar == null)  tabChar  = Constants.EMPTY;  // empty out
        
        // if no name..
        if(!(parent() instanceof JsonArray)) builder.append(lineChar);
        for(int j = 0; j < rootCount - 1; j++) 
            builder.append(tabChar);
        builder.append(Constants.ARRAY_START).append(lineChar);
        if(value != null && size() > 0){
            for(int i = 0; i < size(); i++){
                Json child = value.get(i);
                
                for(int j = 0; child instanceof JsonType && j < rootCount; j++) 
                    builder.append(tabChar);
                builder.append(child.toString(tabChar, lineChar));
                if(i != size() - 1){
                    builder.append(Constants.COMMA);
                    builder.append(lineChar);
                }

            }
            builder.append(lineChar);
        }
        for(int j = 0; j < rootCount - 1; j++) builder.append(tabChar);
        builder.append(Constants.ARRAY_END);
        //</editor-fold>
        
        return builder.toString();
    }

    //<editor-fold defaultstate="collapsed" desc="java.util.Collection implementation">
    @Override
    public int size() {
        return value.size();
    }

    @Override
    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        o = Bison.jsonify(JsonValue.class, this, o);
        return value.contains((JsonValue)o);
    }

    @Override
    public Iterator<Json> iterator() {
        return value.iterator();
    }

    @Override
    public JsonValue[] toArray() {
        return (JsonValue[])value.toArray();
    }

    @Override
    public <Json> Json[] toArray(Json[] jsons) {
        return value.toArray(jsons);
    }

    @Override
    public boolean add(Json json) {
        return value.add(json);
    }

    @Override
    public boolean remove(Object object) {
        object = Bison.jsonify(Json.class, this, object);
        return value.remove((Json)object);
    }

    @Override
    public boolean containsAll(Collection<?> jsons) {
        return value.containsAll(jsons);
    }

    @Override
    public boolean addAll(Collection<? extends Json> c) {
        return value.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return value.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return value.retainAll(c);
    }

    @Override
    public void clear() {
        value.clear();
    }
    //</editor-fold>
    
}
