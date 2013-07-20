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

package com.bingzer.bison.serializer;

import com.bingzer.bison.JsonException;

/**
 *
 * @author Ricky Tobing
 */
public interface Jsonifiable {
    
    /**
     * Returns all the names..
     * @return 
     */
    CharSequence[] names();
    
    /**
     * Returns the type
     * @param name
     * @return 
     */
    Class<?> typeof(CharSequence name);
    
    /**
     * Returns the value
     * @param name
     * @param value
     * @throws JsonException 
     */
    void value(CharSequence name, Object value) throws JsonException;
    
    /**
     * Returns the value
     * @param name
     * @return
     * @throws JsonException 
     */
    Object value(CharSequence name) throws JsonException;
    
}
