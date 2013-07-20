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

/**
 *
 * @author Ricky Tobing
 */
@   com.bingzer.common.serial.Entity(name="JsonString", browsable=false)
public class JsonString extends JsonType<CharSequence>{
    // <editor-fold defaultstate="collapsed" desc="Code">

    /**
     * New instance of string
     * @param json 
     */
    protected JsonString(Json json){
        super(json);
    }

    /**
     * Parses and return parent
     * @param jsonText
     * @return
     * @throws JsonException 
     */
    @Override
    protected Json parse(CharSequence jsonText) throws JsonException {
        if(jsonText.length() > 1)
            value = jsonText.subSequence(1, jsonText.length() - 1);
        else value = jsonText;

        // look for escape characters
        // make sure they escape that caracter
        for(int i = 1; i < jsonText.length() - 1; i++){
            if(jsonText.charAt(i) == Constants.QUOTE || jsonText.charAt(i) == Constants.DOUBLE_QUOTE)
                if(jsonText.charAt(i - 1) != Constants.SLASH)
                    Bison.syntaxError(this, "Escape character is expected: " + jsonText.charAt(i) + " " +(CharSequence) value);
        }

        return this;
    }

    /**
     * Returns the correct text.
     * @param tabChar 
     * @param lineChar 
     * @return 
     */
    @Override
    public java.lang.String toString(CharSequence tabChar, CharSequence lineChar) {
        StringBuilder builder = new StringBuilder();

        builder.append(Constants.DOUBLE_QUOTE).append(value).append(Constants.DOUBLE_QUOTE);

        return builder.toString();
    }

    /**
     * Overrides value to a String
     * @return 
     */
    @Override
    public java.lang.CharSequence value() {
        return (CharSequence) value;
    }

    /**
     * String vlaue
     * @return 
     */
    public java.lang.String stringValue() {
        return value.toString();
    }


    //</editor-fold>
}
