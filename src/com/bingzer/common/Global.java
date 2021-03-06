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
 * @author Ricky Tobing
 */
public class Global {
    
    /**
     * 
     * @param primitiveArray
     * @return
     */
    public static Object[] toObjectArray(Object primitiveArray){
        if(primitiveArray != null && primitiveArray.getClass().isArray()
                && primitiveArray.getClass().getComponentType().isPrimitive()){
            if(primitiveArray.getClass().getComponentType().getName().equals("int"))
                return toObject((int[]) primitiveArray);
            else if(primitiveArray.getClass().getComponentType().getName().equals("float"))
                return toObject((float[]) primitiveArray);
            else if(primitiveArray.getClass().getComponentType().getName().equals("double"))
                return toObject((double[]) primitiveArray);
            else if(primitiveArray.getClass().getComponentType().getName().equals("long"))
                return toObject((long[]) primitiveArray);
            else if(primitiveArray.getClass().getComponentType().getName().equals("short"))
                return toObject((short[]) primitiveArray);
            else if(primitiveArray.getClass().getComponentType().getName().equals("byte"))
                return toObject((byte[]) primitiveArray);
            else if(primitiveArray.getClass().getComponentType().getName().equals("char"))
                return toObject((char[]) primitiveArray);
            else if(primitiveArray.getClass().getComponentType().getName().equals("boolean"))
                return toObject((boolean[]) primitiveArray);
        }
        
        // otherwise return null
        return null;
     }
     
    /**
     * 
     * @param primitive
     * @return
     */
    public static Object toObject(Object primitive){
        if(primitive != null && primitive.getClass().isArray()){
            if(primitive.getClass().getComponentType().getName().equals("int"))
                return toObject((int) Integer.parseInt("" + primitive));
            else if(primitive.getClass().getComponentType().getName().equals("float"))
                return toObject((float) Float.parseFloat("" + primitive));
            else if(primitive.getClass().getComponentType().getName().equals("double"))
                return toObject((double) Double.parseDouble("" + primitive));
            else if(primitive.getClass().getComponentType().getName().equals("long"))
                return toObject((long) Long.parseLong("" + primitive));
            else if(primitive.getClass().getComponentType().getName().equals("short"))
                return toObject((short) Short.parseShort("" + primitive));
            else if(primitive.getClass().getComponentType().getName().equals("byte"))
                return toObject((byte) Byte.parseByte("" + primitive));
            else if(primitive.getClass().getComponentType().getName().equals("char"))
                return toObject((char) ("" + primitive).toCharArray()[0]);
            else if(primitive.getClass().getComponentType().getName().equals("boolean"))
                return toObject((boolean) Boolean.parseBoolean("" + primitive));
        }
        return null;
    }
    
     /**
      * 
      * @param primitive
      * @return
      */
     public static Integer toObject(int primitive){
        return new Integer(primitive);
    }
    
     /**
      * 
      * @param primitive
      * @return
      */
     public static Float toObject(float primitive){
        return new Float(primitive);
    }
    
     /**
      * 
      * @param primitive
      * @return
      */
     public static Double toObject(double primitive){
        return new Double(primitive);
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Long toObject(long primitive){
        return new Long(primitive);
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Short toObject(short primitive){
        return new Short(primitive);
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Byte toObject(byte primitive){
        return new Byte(primitive);
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Character toObject(char primitive){
        return new Character(primitive);
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Boolean toObject(boolean primitive){
        return primitive;
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Float[] toObject(float[] primitive){
        Float[] obj = new Float[primitive.length];
        for(int i = 0; i < primitive.length; i++){
            obj[i] = primitive[i];
        }
        return obj;
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Boolean[] toObject(boolean[] primitive){
        Boolean[] obj = new Boolean[primitive.length];
        for(int i = 0; i < primitive.length; i++){
            obj[i] = primitive[i];
        }
        return obj;
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Integer[] toObject(int[] primitive){
        Integer[] obj = new Integer[primitive.length];
        for(int i = 0; i < primitive.length; i++){
            obj[i] = primitive[i];
        }
        return obj;
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Long[] toObject(long[] primitive){
        Long[] obj = new Long[primitive.length];
        for(int i = 0; i < primitive.length; i++){
            obj[i] = primitive[i];
        }
        return obj;
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Double[] toObject(double[] primitive){
        Double[] obj = new Double[primitive.length];
        for(int i = 0; i < primitive.length; i++){
            obj[i] = primitive[i];
        }
        return obj;
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Short[] toObject(short[] primitive){
        Short[] obj = new Short[primitive.length];
        for(int i = 0; i < primitive.length; i++){
            obj[i] = primitive[i];
        }
        return obj;
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Byte[] toObject(byte[] primitive){
        Byte[] obj = new Byte[primitive.length];
        for(int i = 0; i < primitive.length; i++){
            obj[i] = primitive[i];
        }
        return obj;
    }
    
    /**
     * 
     * @param primitive
     * @return
     */
    public static Character[] toObject(char[] primitive){
        Character[] obj = new Character[primitive.length];
        for(int i = 0; i < primitive.length; i++){
            obj[i] = primitive[i];
        }
        return obj;
    }
}
