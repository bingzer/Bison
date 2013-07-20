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

import com.bingzer.bison.commons.StringUtil;

/**
 * Top link of any Json object. Bison API threats everything as a Json object
 * which inherits directly from this abstract class.
 *
 * @param <E> 
 * @author Ricky Tobing
 */
@com.bingzer.common.serial.Entity(name="JsonValue", browsable=false)
public abstract class JsonValue<E> implements Json{
    
    //<editor-fold defaultstate="collapsed" desc="Private/Protected data members">
    private Json    parent       = null;
    private boolean browsable    = true;
    /**
     * 
     */
    protected E     value        = null;
    //</editor-fold>
    
    /**
     * Creates an abstract Json object with its parent
     * 
     * @param parent the parent
     */
    protected JsonValue(Json parent){
        this.parent = parent;
    }
    
    /**
     * Append any to this Json object
     * @param any 
     * @return
     * @throws JsonException  
     */
    protected abstract Json append(Object any) throws JsonException;
    
    /**
     * Appends multiple
     */
    @Override
    public final Json append(Object... any){
        for(int i = 0; i < any.length; i++){
            append(any[i]);
        }
        
        return this;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public final <T extends Json> T as(Class<T> jsonType){
        return (T) this;
    }
    
    /**
     * Returns the root of the Json tree. Root is defined as
     * any abstract Json object whose parent is null. It is
     * recommended to 'root' any Json object to print out its
     * text.
     * 
     * @return the root.
     */
    @Override
    public Json root(){
        Json p = (Json) this;
        while(p.parent() != null){
            p = p.parent();
        }
        
        return p;
    }
    
    /**
     * Returns the parent of this Json object
     * @return 
     */
    @Override
    public Json parent(){
        return parent;
    }
    
    /**
     * Search thru the tree of this parent.
     * If no such parent found with such name, it should return
     * the immediate parent. name is case sensitive
     * @param name
     * @return 
     */
    @Override
    public Json parent(CharSequence name){
        Json p = parent;
        if(p != null && p.parent() == null)
            p = this;
        
        while(p != null && p instanceof Json){
            if(p instanceof JsonPair &&
                    p.as(JsonPair.class).name() != null && StringUtil.equals(p.as(JsonPair.class).name(), name))
                return p;
            p = p.parent();
        }
        
        return p == null ? parent : p;
    }
    
    /**
     * Sets/Modifies the Json 
     * @param any 
     * @return 
     */
    @Override
    @SuppressWarnings("unchecked")
    public Json value(Object any){
        this.value = (E) Bison.jsonify(this, any);
        return this;
    }
    
    /**
     * Returns any Json that this Json has
     * @return Json
     */
    @Override
    public E value(){
        return value;
    }
    
    /**
     * Convert the following valueAs as the jsonType.
     * Be careful.
     * <p>
     * List of Json objects
     * <ul>
     * <li>JsonObject.class</li>
     * <li>JsonArray.class</li>
     * <li>JsonPair.class</li>
     * <li>JsonValue.Number.class</li>
     * <li>JsonValue.String.class</li>
     * <li>JsonValue.Boolean.class</li>
     * <li>JsonValue.Date.class</li>
     * <li>JsonValue.Null.class</li>
     * </ul>
     * @param <T>
     * @param jsonType  
     */
    @Override
    @SuppressWarnings("unchecked")
    public final <T extends Json> T valueAs(Class<T> jsonType){
        return (T) this.value;
    }
    
    /**
     * Returns the correct representation of Json
     * 
     * @return Json Text
     */
    @Override
    public final String toString(){
        return toString(null, null);
    }
    
    /**
     * Returns the formatted Json with tabChar
     * @param tabChar
     * @return 
     */
    public String toString(CharSequence tabChar){
        return toString(tabChar, Bison.Setting.getLineChar());
    }
    
    /**
     * Returns formatted Json with new line and everything
     * @param tabChar 
     * @param lineChar 
     * @return 
     */
    public abstract String toString(CharSequence tabChar, CharSequence lineChar);
    
    /**
     * Find child/pair that has the same name
     * @param name
     * @return 
     * @throws JsonException  
     */
    @Override
    public abstract JsonPair find(CharSequence name) throws JsonException;
    
    /**
     * Adds a find
     */
    @Override
    public abstract Json pair(CharSequence name, Object any) throws JsonException;
    
    /**
     * Returns true if this object is set to be
     * a browsable object. Default valueAs is true
     * @return 
     */
    public boolean isBrowsable(){
        return browsable;
    }
    
    /**
     * Checks equality with other Json object
     * @param obj object to compare
     * @return true/false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Json)
            return hashCode() == obj.hashCode();
        // return false..
        return false;
    }

    /**
     * Returns hash code.
     * We're not comparing the parent.
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.browsable ? 1 : 0);
        hash = 37 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }
    
    /**
     * Returns the number of parents until it reaches the root
     * @return 
     */
    protected int rootCount(){
        int rootCount = 0;
        Json p = this;
        while(p.parent() != null){
            rootCount++;
            p = p.parent();
        }
        return rootCount;
    }
    
    /**
     * Sets/Modifies browsable
     * @param browsable 
     */
    protected void setBrowsable(boolean browsable){
        this.browsable = browsable;
    }    
    
    /**
     * Parses any Json text and returns itself
     * @param jsonText 
     * @return this
     * @throws JsonException  
     */
    protected abstract Json parse(CharSequence jsonText) throws JsonException;
    
    //<editor-fold defaultstate="collapsed" desc="Static fields/Methods">
    
  
    //</editor-fold>
}
