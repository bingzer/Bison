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
public class JsonStringTest {
    
    Json json = null;
    CharSequence jsonText = null;
    
    /**
     * 
     */
    public JsonStringTest() {
        System.out.println(">>Running: JsonValue.String Test");
    }
    
    /**
     * 
     */
    @Test
    public void testString(){
        jsonText = "'this is a string'";
        json = Bison.jsonify(jsonText);
        
        // with quote
        assertTrue(json.toString().equals("\"this is a string\""));
    }
    
    /**
     * 
     */
    @Test
    public void testValue(){
        json = Bison.jsonify("'this is a string'");
        assertTrue(json.as(JsonString.class).value() instanceof CharSequence);
    }
    
    /**
     * 
     */
    @Test(expected=JsonException.class)
    public void testException(){
        // this is a string without quote
        json = Bison.jsonify("this is a string");
    }
    
}
