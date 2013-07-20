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

/**
 * This is an unchecked exception that runs on Runtime
 *
 * @author Ricky Tobing
 */
public class JsonException extends RuntimeException{
    
    /**
     * 
     */
    public JsonException(){
        this((String)null);
    }
    
    /**
     * 
     * @param message
     */
    public JsonException(CharSequence message){
        this(message.toString());
    }
    
    /**
     * 
     * @param message
     */
    public JsonException(String message){
        this(message, (Throwable) null);
    }
    
    /**
     * 
     * @param any
     */
    public JsonException(Throwable any){
        this(null, any);
    }
    
    /**
     * 
     * @param message
     * @param any
     */
    public JsonException(String message, Throwable any){
        super(message);
        initCause(any);
    }
}
