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
 * Represents any Null value
 *
 * @author Ricky Tobing
 */
@   com.bingzer.common.serial.Entity(name="JsonNull", browsable=false)
public class JsonNull extends JsonType<Object>{
    // <editor-fold defaultstate="collapsed" desc="Code">
    /**
     * New instance of string
     * @param json 
     */
    protected JsonNull(Json json){
        super(json);
    }

    /**
     * Parses and return parent. 
     * @param jsonText
     * @return
     * @throws JsonException 
     */
    @Override
    protected Json parse(CharSequence jsonText) throws JsonException {
        // no use to parse
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
        return Constants.NULL.toString();
    }

    //</editor-fold>
}
