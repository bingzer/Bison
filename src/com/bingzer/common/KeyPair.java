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
public class KeyPair<K extends CharSequence, V> implements Comparable<KeyPair> {
    
    private K name;
    private V value;
    private boolean readOnly = false;
    
    /**
     * New instnace of key pair
     * @param name
     * @param defaultValue 
     */
    public KeyPair(K name, V defaultValue){
        this(name, defaultValue, false);
    }
    
    /**
     * New instance of a 'read-only' pair
     * @param name
     * @param defaultValue
     * @param readOnly 
     */
    public KeyPair(K name, V defaultValue, boolean readOnly){
        this.name     = name;
        this.value    = defaultValue;
        this.readOnly = readOnly;
    }
    
    /**
     * Returns the name
     * @return 
     */
    public K name(){
        return name;
    }
    
    /**
     * Sets the value and return this
     * @param value
     * @return 
     */
    public KeyPair value(V value){
        if(!readOnly) this.value = value;
        return this;
    }
    
    /**
     * Returns the value
     * @return 
     */
    public V value(){
        return value;
    }
    
    /**
     * Checks to see if <code>value</code>
     * has the same type as the original type class
     * @param value
     * @return  
     */
    public boolean hasSameType(Object value){
        if(value != null && this.value != null)
            return (value.getClass() == this.value.getClass());
        return false;
    }
    
    /**
     * Check to see if this pair is read-only
     * @return 
     */
    public boolean isReadOnly(){
        return readOnly;
    }

    /**
     * Compare to other keypair
     * @param o
     * @return 
     */
    @Override
    public int compareTo(KeyPair o) {
        if(o != null && name != null){
            return (name).toString().compareTo(o.name.toString());
        }
        return -1;
    }

    /**
     * equals
     * @param obj
     * @return e
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof KeyPair){
            return obj.hashCode() == hashCode();
        }
        
        return false;
    }

    /**
     * Hash code
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 31 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }
    
}
