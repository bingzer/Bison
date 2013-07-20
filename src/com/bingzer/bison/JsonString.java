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
