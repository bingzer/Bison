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

import com.bingzer.bison.commons.JsonUtil;

/**
 * Represents Json boolean (true/false)
 *
 * @author Ricky Tobing
 */
public class JsonBoolean extends JsonType<java.lang.Boolean>{
    // <editor-fold defaultstate="collapsed" desc="Code">
    /**
     * New instance of string
     * @param json 
     */
    protected JsonBoolean(Json json){
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
        value = JsonUtil.toBoolean(jsonText);
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
        return value.toString();
    }

    /**
     * Overrides value with boolean
     * @return 
     */
    @Override
    public java.lang.Boolean value() {
        return (java.lang.Boolean) value;
    }

    /**
     * Returns boolean
     * @return 
     */
    public boolean booleanValue(){
        return this.value().booleanValue();
    }

    // </editor-fold>
}
