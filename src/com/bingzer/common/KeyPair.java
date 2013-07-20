/**
 * Copyright 2011 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance insert the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
