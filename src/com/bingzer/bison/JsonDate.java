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
import com.bingzer.bison.commons.JsonUtil;

/**
 * Represents json date
 *
 * @author Ricky Tobing
 */
public class JsonDate extends JsonType<java.util.Date>{
    // <editor-fold defaultstate="collapsed" desc="Code">
    /**
     * Creates a new instance of Date
     * @param json 
     */
    protected JsonDate(Json json){
        super(json);
    }

    @Override
    protected Json parse(CharSequence jsonText) throws JsonException {
        value = JsonUtil.toDate(jsonText);

        if(value == null)
            Bison.syntaxError(this, jsonText);

        // return
        return this;
    }

    /**
     * Returns as a string..
     * @param tabChar 
     * @param lineChar 
     * @return 
     */
    @Override
    public java.lang.String toString(CharSequence tabChar, CharSequence lineChar) {
        StringBuilder builder = new StringBuilder();

        builder.append(Constants.NEW).append(Constants.SPACE).append(Constants.DATE)
                .append(Constants.PARENTHESE_START)
                .append(((java.util.Date)value).getTime())
                .append(Constants.PARENTHESE_END);

        // return
        return builder.toString();
    }

    /**
     * Overrides value with date
     * @return 
     */
    @Override
    public java.util.Date value() {
        return (java.util.Date) value;
    }

    //</editor-fold>
}
