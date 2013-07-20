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
