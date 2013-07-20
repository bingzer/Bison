/*
 *  Copyright 2011 Ricky Tobing.
 *
 *  The codes provided is part of a Project Bingzer API.
 *  Free to use. Use as is since there is no warranty
 *  that code integration with your own code should always
 *  work the way they intended to do.
 *
 *  Please report any bugs to:
 *               ricky@bingzer.com
 *  Follow my blogs 
 *               http://blog.bingzer.com
 *
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
