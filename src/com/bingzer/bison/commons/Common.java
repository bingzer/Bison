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

package com.bingzer.bison.commons;

import com.bingzer.common.Global;
import java.util.Collection;

/**
 *
 * @author Ricky Tobing
 */
public class Common {
    
    /**
     * Check to see if <code>cl</code> is part of Number class.
     * e.g: Integer, Long, Short, Float, Double
     * @param cl
     * @return 
     */
    public static boolean isNumberClass(Class<?> cl){
        return cl == Short.class || cl == short.class || 
                cl == Integer.class || cl == int.class ||
                cl == Long.class || cl == long.class ||
                cl == Float.class || cl == float.class ||
                cl == Double.class || cl == double.class ||
                cl == Byte.class || cl == byte.class;
    }
    
     /**
     * Checks to see if any is considered to be a jsonArray.
     * jsonArray can be native java array or any instance of java.util.Collection
     * @param any
     * @return 
     */
    public static boolean isObjectArray(Object any){
        if(any == null) return false;
        else if(any.getClass().isArray())
            return true;
        else if(any instanceof Collection<?>)
            return true;
        // -- otherwise return false
        return false;
    }
    
    /**
     * To object array
     * @param any
     * @return 
     */
    public static Object[] toObjectArray(Object any){
        if(any == null)
            return null;
        else if(any instanceof Collection<?>)
            any = ((Collection<?>)any).toArray();
        else if(any.getClass().isPrimitive() || any.getClass().getComponentType().isPrimitive())
            any = Global.toObjectArray(any);
        return (Object[])any;
    }
    
    
}
