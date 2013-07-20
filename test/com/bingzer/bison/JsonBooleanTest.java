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
public class JsonBooleanTest {
    
    Json json = null;
    CharSequence jsonText = null;
    
    /**
     * 
     */
    public JsonBooleanTest() {
        System.out.println(">>Running: JsonValue.Boolean Test");
    }
    
    /**
     * 
     */
    @Test
    public void TestFalse(){
        json = Bison.jsonify("false");
        // test value
        assertTrue(!((Boolean)json.value()));
        // test string
        assertTrue(json.toString().equals("false"));
    }
    
    /**
     * 
     */
    @Test
    public void TestTrue(){
        json = Bison.jsonify("true");
        // test value
        assertTrue(((Boolean)json.value()));
        // test string
        assertTrue(json.toString().equals("true"));
    }
    
    /**
     * 
     */
    @Test
    public void testValue(){
        json = Bison.jsonify(true);
        assertTrue(json.as(JsonBoolean.class).value() instanceof Boolean);
    }
    
    /**
     * 
     */
    @Test(expected=JsonException.class)
    public void TestException(){
        json = Bison.jsonify("FALSE");
        // test value
        assertTrue(!((Boolean)json.value()));
        // test string
        assertTrue(json.toString().equals("false"));
    }
}
