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
public class JsonNullTest {
    
    Json json = null;
    CharSequence jsonText = null;
    
    /**
     * 
     */
    public JsonNullTest() {
        System.out.println(">>Running: JsonValue.Null Test");
    }
    
    /**
     * 
     */
    @Test
    public void TestNull(){
        json = Bison.jsonify("null");
        // test value
        assertTrue(json.value() == null);
        // test string
        assertTrue(json.toString().equals("null"));
    }
}
