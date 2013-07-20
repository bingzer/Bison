/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingzer.bison;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Ricky Tobing
 */
public class JsonNumberTest {
    
    
    Json json = null;
    CharSequence jsonText = null;
    
    /**
     * 
     */
    public JsonNumberTest() {
        System.out.println(">>Running: JsonValue.Number Test");
    }
    
    /**
     * 
     */
    @Test
    public void testInteger(){
        json = Bison.jsonify("1");
        
        // with quote
        assertTrue((Integer)json.value()== 1);
    }
    
    /**
     * 
     */
    @Test
    public void testLong(){
        json = Bison.jsonify(((long)Integer.MAX_VALUE + (long)100) + "");
        
        // with quote
        assertTrue((Long)json.value()== ((long)Integer.MAX_VALUE + 100));
    }
    
    /**
     * 
     */
    @Test
    public void testFloat(){
        json = Bison.jsonify("1.0");
        
        // with quote
        assertTrue((Float)json.value()== 1.0f);
    }
    
    /**
     * 
     */
    @Test
    public void testDouble(){
        double number = Double.MAX_VALUE;
        json = Bison.jsonify(number + "");
        
        // with quote
        assertTrue((Double)json.value()== number);
    }
    
    /**
     * 
     */
    @Test
    public void testValue(){
        json = Bison.jsonify(123445);
        assertTrue(json.as(JsonNumber.class).value() instanceof Number);
    }
}
