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
