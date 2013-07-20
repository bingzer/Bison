/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingzer.bison;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricky Tobing
 */
public class JsonDateTest {
    
    Json json = null;
    CharSequence jsonText = null;
    
    /**
     * 
     */
    public JsonDateTest() {
        System.out.println(">>Running: JsonValue.Date Test");
    }
    
    /**
     * 
     */
    @Test
    public void testValue(){
        json = Bison.jsonify(new Date(System.currentTimeMillis()));
        assertTrue(json.as(JsonDate.class).value() instanceof java.util.Date);
    }
    
    /**
     * 
     */
    @Test
    public void TestDate(){
        long millis = System.currentTimeMillis();
        jsonText = "new Date(" + millis + ")";
        
        json = Bison.jsonify(jsonText);
        assertTrue(((Date)json.value()).getTime() == millis);
    }
}
