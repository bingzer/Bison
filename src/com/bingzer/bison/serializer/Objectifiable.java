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

import com.bingzer.bison.Json;
import com.bingzer.bison.JsonException;

/**
 *
 * @author Ricky Tobing
 */
public interface Objectifiable {
    
    /**
     * 
     * @param <T>
     * @param json
     * @param any
     * @return
     * @throws JsonException
     */
    <T> T toObject(Json json, T any) throws JsonException;
    
    /**
     * 
     * @param object
     * @param parent
     * @return
     * @throws JsonException
     */
    Json toJson(Object object, Json parent) throws JsonException;
    
}
