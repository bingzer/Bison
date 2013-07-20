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
public class Test_ArrayTest {
    
    Json json;
    
    /**
     * 
     */
    public Test_ArrayTest() {
        System.out.println(">>Running: Test -- Array");
    }
    
    /**
     * 
     */
    @Test public void testArrayGeneral(){
        json = Bison.jsonify(new String[]{"'string1'","'string2'","'string3'"});
        System.out.println(json);
        json.as(JsonArray.class).valueReplace("'string1'", "'string0'");
        System.out.println(json);
    }
    
    /**
     * 
     */
    @Test public void testArrayString(){
        json = Bison.jsonify(new String[]{"'string1'","'string2'","'string3'"});
        assertTrue(json.as(JsonArray.class).size() == 3);
        assertTrue(json.as(JsonArray.class).valueAt(0).value().toString().equals("string1"));
        assertTrue(json.as(JsonArray.class).valueAt(1).value().toString().equals("string2"));
        assertTrue(json.as(JsonArray.class).valueAt(2).value().toString().equals("string3"));
    }
    
    /**
     * 
     */
    @Test public void testArrayInteger(){
        json = Bison.jsonify(new int[]{1,2,3,4,5,6,7,8,9,10});
        assertTrue(json.as(JsonArray.class).size() == 10);
        assertTrue(json.as(JsonArray.class).valueAt(0).as(JsonNumber.class).intValue() == 1);
        assertTrue(json.as(JsonArray.class).valueAt(5).as(JsonNumber.class).intValue() == 6);
        assertTrue(json.as(JsonArray.class).valueAt(9).as(JsonNumber.class).intValue() == 10);
    }
    
    /**
     * 
     */
    @Test public void testArrayAny(){
        Object[] array = 
        {
            100,
            "'string'",
            false,
            "pair:true"
        };
        json = Bison.jsonify(array);
        assertTrue(json.as(JsonArray.class).size() == 4);
        
        // change the first index to 150
        json.as(JsonArray.class).valueAt(0, (Object) 155);
        json.as(JsonArray.class).valueAt(1, (Object) 150);
        json.as(JsonArray.class).valueAt(0).value(150);
        assertTrue(json.as(JsonArray.class).valueAt(0).as(JsonNumber.class).intValue() == 150);
        assertTrue(json.as(JsonArray.class).valueAt(1).as(JsonNumber.class).intValue() == 150);
        assertTrue(json.as(JsonArray.class).valueAt(3).as(JsonPair.class).name().equals("pair"));
    }
    
    /**
     * Test find() in JsonArray
     */
    @Test public void testArrayFind(){
        Object[] array = 
        {
            100,
            "'string'",
            false,
            "pair:true"
        };
        json = Bison.jsonify(array);
        assertTrue(json.as(JsonArray.class).size() == 4);
        
        // change the first index to 150
        assertTrue(json.as(JsonArray.class).find("pair") != null);
        assertTrue(json.as(JsonArray.class).value(100) != null);
    }
    
    /**
     * Test to check value assignment
     */
    @Test(expected=com.bingzer.bison.JsonException.class) 
    public void testValueAssignString(){
        Object[] array = {"'string'"};
        json = Bison.jsonify(array);
        assertTrue(json.as(JsonArray.class).size() == 1);
        // we're expecting to assign a string NOT a boolean
        json.as(JsonArray.class).valueAt(0).value(true);
    }
    
    /**
     * 
     */
    @Test(expected=com.bingzer.bison.JsonException.class) 
    public void testValueAssignBoolean(){
        Object[] array = {true};
        json = Bison.jsonify(array);
        assertTrue(json.as(JsonArray.class).size() == 1);
        // we're expecting to assign a boolean NOT an integer
        json.as(JsonArray.class).valueAt(0).value(11);
    }
    
    /**
     * 
     */
    @Test(expected=com.bingzer.bison.JsonException.class) 
    public void testValueAssignInteger(){
        Object[] array = {100};
        json = Bison.jsonify(array);
        assertTrue(json.as(JsonArray.class).size() == 1);
        // we're expecting to assign a integer NOT a boolean
        json.as(JsonArray.class).valueAt(0).value(true);
        json.as(JsonArray.class).value(true);
    }
}
