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
 * Represents Json number. This class is fully represented by 
 * <code>java.lang.Number</code>
 *
 * @author Ricky Tobing
 * @see java.lang.Number
 */
@   com.bingzer.common.serial.Entity(name="JsonNumber", browsable=false)
public class JsonNumber extends JsonType<Number>{
    // <editor-fold defaultstate="collapsed" desc="Code">
    /**
     * New instance of string
     * @param json 
     */
    protected JsonNumber(Json json){
        super(json);
    }

    /**
     * Parses this to number
     * @param jsonText
     * @return
     * @throws JsonException 
     */
    @Override
    protected Json parse(CharSequence jsonText) throws JsonException {
        value = JsonUtil.toNumber(jsonText); 

        // return parent..
        return this;
    }

    /**
     * To string..
     * @param tabChar 
     * @param lineChar 
     * @return 
     */
    @Override
    public java.lang.String toString(CharSequence tabChar, CharSequence lineChar) {
        return value.toString();
    }

    /**
     * Overrides value
     * @return 
     */
    @Override
    public java.lang.Number value() {
        return (java.lang.Number) value;
    }

    /**
     * Returns integer value
     * @return 
     */
    public int intValue(){
        return ((java.lang.Number)value).intValue();
    }

    /**
     * Double
     * @return 
     */
    public double doubleValue(){
        return ((java.lang.Number)value).doubleValue();
    }

    /**
     * Float value
     * @return 
     */
    public float floatValue(){
        return ((java.lang.Number)value).floatValue();
    }

    /**
     * Long value
     * @return 
     */
    public long longValue(){
        return ((java.lang.Number)value).longValue();
    }

    /**
     * Short value
     * @return 
     */
    public short shortValue(){
        return ((java.lang.Number)value).shortValue();
    }

    /**
     * Bytes value
     * @return 
     */
    public byte byteValue(){
        return ((java.lang.Number)value).byteValue();
    }

    //</editor-fold>
}
