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
