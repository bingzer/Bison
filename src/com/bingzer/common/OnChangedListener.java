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

package com.bingzer.common;

/**
 *
 * @param <K> 
 * @param <V> 
 * @author Ricky Tobing
 */
public interface OnChangedListener<K,V> {
    
    /**
     * Invoked when 'something' changed
     * @param name
     * @param newValue
     * @param oldValue 
     */
    void onChanged(K name, V newValue, V oldValue);
}
