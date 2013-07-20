/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingzer.bison;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricky Tobing
 */
public class JsonPairTest {
    
    Json json = null;
    CharSequence jsonText = null;
    
    /**
     * 
     */
    public JsonPairTest() {
        System.out.println(">>Running: JsonPair Test");
    }

    /**
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * 
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    /**
     * 
     */
    @Before
    public void setUp() {
        json = Bison.jsonify(JsonValue.PAIR);
        assertTrue(json instanceof JsonPair);
        json = Bison.jsonify("");
        assertTrue(json instanceof JsonPair);
    }

    /**
     * 
     */
    @Test
    public void testAppend() {
        json = Bison.jsonify("");    // let's create a new pair
        assertTrue(json.append("{}") == json);
        assertTrue(json.value()instanceof JsonObject);
    }

    /**
     * 
     */
    @Test
    public void testValue() {
        json = Bison.jsonify("");    // let's create a new pair
        assertTrue(json.append("[]") == json);
        assertTrue(json.value()instanceof JsonArray);
    }

    /**
     * 
     */
    @Test
    public void testPair() {
        json = Bison.jsonify(JsonValue.PAIR);
        assertTrue(json.pair("harold", "'kumar'") == json);
        assertTrue(json.find("harold").value() instanceof JsonString);
    }

    /**
     * 
     */
    @Test
    public void testFind() {
        json = Bison.jsonify("");
        assertTrue(json.pair("whiteCastle", "{}") == json);
        assertTrue(json.find("whiteCastle").value()instanceof JsonObject);
    }

    /**
     * 
     */
    @Test
    public void testParse() {
        json = new JsonPair(null);
        json.as(JsonValue.class).parse("whiteCastle:'i love burgers'");
        assertTrue(json.toString().equals("\"whiteCastle\":\"i love burgers\""));
        
        Json json2 = Bison.jsonify("whiteCastle:'i love burgers'");
        assertTrue(json.value().equals(json2.value())); // whiteCastle == whiteCastle
    }

    /**
     * 
     */
    @Test
    public void testToString() {
        json = Bison.jsonify("a:true");
        assertTrue(json.toString().equals("\"a\":true"));
    }
}
