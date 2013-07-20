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
 * Data types are:
 * <ul>
 *  <li>{@link JsonString}</li>
 *  <li>{@link JsonBoolean}</li>
 *  <li>{@link JsonNumber}</li>
 *  <li>{@link JsonDate}</li>
 *  <li>{@link JsonNull}</li>
 * </ul>
 *
 * @param <E> 
 * @author Ricky Tobing
 */
@com.bingzer.common.serial.Entity(name="JsonType", browsable=false)
public abstract class JsonType<E> extends JsonValue<E>{
    
    /**
     * Creates new instance of Json valueOf
     * @param parent 
     */
    protected JsonType(Json parent){
        super(parent);
    }
    
    
    /**
     * This method will do nothing
     * @param any 
     * @return 
     * @throws JsonException 
     * @deprecated Can't append anything to a JsonType
     */
    @Deprecated
    @Override
    protected final Json append(Object any) throws JsonException {
        return this;
    }
    
    /**
     * Find a find. Returns this
     * @deprecated Can't find any pair
     * @param name
     * @return 
     */
    @Override
    @Deprecated
    public final JsonPair find(CharSequence name) {
        return null;
    }

    /**
     * Not used
     * @param name
     * @param value 
     * @return
     * @throws JsonException 
     * @deprecated 
     */
    @Override
    @Deprecated
    public final Json pair(CharSequence name, Object value) throws JsonException {
        return this;
    }
        
    /**
     * Overrides this..
     * @param any
     * @return 
     */
    @Override
    @SuppressWarnings("unchecked")
    public Json value(Object any) {
        Json val = Bison.jsonify(Json.class, this, any);
        if(! (val.getClass().equals(getClass())))
            Bison.syntaxError(this, "Not a " + getClass().getSimpleName() + ": " + val);

        this.value = (E) val.value();

        return val;
    }

    /**
     * Parses Json text
     * 
     * @param jsonText
     * @return
     * @throws JsonException 
     */
    @Override
    protected abstract Json parse(CharSequence jsonText) throws JsonException;

    /**
     * Returns the string
     * @param tabChar
     * @param lineChar  
     */
    @Override
    public abstract java.lang.String toString(CharSequence tabChar, CharSequence lineChar);
    
}
