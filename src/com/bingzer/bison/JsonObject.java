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
import java.util.LinkedList;
import java.util.List;

/**
 * JsonObject.valueAs is a List of Json type
 *
 * @author Ricky Tobing
 */
@com.bingzer.common.serial.Entity(name="JsonObject", browsable=false)
public class JsonObject extends JsonValue<List<JsonValue>>{
    
    /**
     * New instance of json object
     * @param json 
     */
    protected JsonObject(Json json){
        super(json);
        value = new LinkedList<JsonValue>();
    }

    /**
     * Append any object to this Json object
     * @param any
     * @return
     * @throws JsonException 
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Json append(Object any) throws JsonException {
        JsonValue json = Bison.jsonify(JsonValue.class, this, any);
        
        // ok, this is just not allowed..
        if(json instanceof JsonType)
            Bison.syntaxError(this, "appending " + json.getClass().getSimpleName() + " is not allowed in this context : " + json.toString());
        
        value.add(json);
        
        // return me
        return this;
    }

    /**
     * Find a find
     * @param name
     * @return 
     */
    @Override
    @SuppressWarnings("unchecked")
    public JsonPair find(CharSequence name) {
        JsonPair json = null;
        for(int i = 0; i < value.size(); i++){
            if(json == null){
                Json child = value.get(i);
                json = child.find(name);
            }
            else
                break;
        }
        
        return json;
    }

    /**
     * Adds a find to this json object
     * @param name
     * @param value 
     * @return
     * @throws JsonException 
     */
    @Override
    @SuppressWarnings("unchecked")
    public Json pair(CharSequence name, Object value) throws JsonException {
        // creates a json find
        Json json = new JsonPair(this).name(name);
        json.value(Bison.jsonify(Json.class, json, value));
        append(json);
        
        return this;
    }

    /**
     * Parse json object
     * @param jsonText
     * @return
     * @throws JsonException 
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Json parse(CharSequence jsonText) throws JsonException{
        jsonText = StringUtil.trim(jsonText);
        
        // must start with { and end with }
        if(!StringUtil.startsWith(jsonText, Constants.START) && !StringUtil.endsWith(jsonText, Constants.END))
            Bison.syntaxError(this, jsonText);
        
        // clear the value
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
            else if(!insideString && c == Constants.END && (i != jsonText.length() - 1)) startCounter--;
            else if(!insideString && c == Constants.ARRAY_START) arrayCounter++;
            else if(!insideString && c == Constants.ARRAY_END) arrayCounter--;
            
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
            // only add when length is over 0
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
     * Returns the length of the array
     * @return 
     */
    @SuppressWarnings("unchecked")
    public int length(){
        return value.size();
    }

    /**
     * To formatted string
     * @param tabChar
     * @param lineChar
     * @return 
     */
    @Override
    @SuppressWarnings("unchecked")
    public java.lang.String toString(CharSequence tabChar, CharSequence lineChar) {
        StringBuilder builder = new StringBuilder();
        
        //<editor-fold defaultstate="collapsed" desc="Code">
        int rootCount = tabChar == null && lineChar == null ? 0 : rootCount();
        
        // empty both if null so we don't get 'null' string
        if(lineChar == null) lineChar = Constants.EMPTY; // empty out
        if(tabChar == null)  tabChar  = Constants.EMPTY;  // empty out
        
        if(!(parent() instanceof JsonArray)) builder.append(lineChar);
        for(int j = 0; j < rootCount - 1; j++) 
            builder.append(tabChar);
        builder.append(Constants.START).append(lineChar);
        if(value != null && value.size() > 0){
            for(int i = 0; i < value.size(); i++){
                JsonValue child = value.get(i);
                
                for(int j = 0; child instanceof JsonType && j < rootCount + 1; j++) 
                    builder.append(tabChar);

                builder.append(child.toString(tabChar, lineChar));
                if(i != value.size() - 1){
                    builder.append(Constants.COMMA);
                    builder.append(lineChar);
                }
                    

            }
            builder.append(lineChar);
        }
        for(int j = 0; j < rootCount - 1; j++) builder.append(tabChar);
        builder.append(Constants.END);
        //</editor-fold>
        
        return builder.toString();
    }
    
}
