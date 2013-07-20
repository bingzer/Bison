/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingzer.bison;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricky Tobing
 */
public class JsonArrayTest {
    
    Json json = null;
    CharSequence jsonText = null;
    
    /**
     * 
     */
    public JsonArrayTest() {
        System.out.println(">>Running: JsonArray Test");
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
        json = Bison.jsonify(Json.ARRAY);
        assertTrue(json instanceof JsonArray);
        json = Bison.jsonify("[]");
        assertTrue(json instanceof JsonArray);
        json = Bison.jsonify(Json.ARRAY);
        assertTrue(json.append(0,1,2,3,4,5,6,7,8,10) == json); // ref must be the same
    }

    /**
     * 
     */
    @Test
    public void testAppend() {
        json.as(JsonValue.class).parse("[]"); // reparse..
        json.append(1).append(2).append(3);
        int len = json.as(JsonArray.class).size();
        assertTrue(len == 3); // length == 10
    }
    
    /**
     * 
     */
    public void testAppendMultiple(){
        json.append(1,2,3,4,5);
        int len = json.as(JsonArray.class).size();
        assertTrue(len == 5);
    }

    /**
     * 
     */
    @Test
    public void testValue() {
        assertTrue(json.value() instanceof java.util.List);
    }

    /**
     * 
     */
    @Test
    public void testValueAt() {
        int index = 5;
        json.as(JsonArray.class).valueAt((int)index, (Object)100);
        assertTrue(json.as(JsonArray.class).valueAt(index).as(JsonNumber.class).intValue() == 100);
    }

    /**
     * 
     */
    @Test
    public void testLength() {
        assertTrue(json.as(JsonArray.class).size() == 10);
        json.as(JsonArray.class).append(11);
        assertTrue(json.as(JsonArray.class).size() == 11);
    }

    /**
     * 
     */
    @Test
    public void testValues() {
        assertTrue(json.as(JsonArray.class).size() == 10);
        assertTrue(json.as(JsonArray.class).value().size() == 10);
        for(Json child : json.as(JsonArray.class).value()){
            assertTrue(child.value() instanceof Integer);
        }
    }

    /**
     * 
     */
    @Test
    public void testPair() {
        json = Bison.jsonify("[]");
        json.pair("what", "'Santa Claus'");
        assertTrue(json.find("what") instanceof JsonPair);
        assertTrue((Boolean)json.find("what").value().toString().equals("\"Santa Claus\""));
    }

    /**
     * 
     */
    @Test
    public void testFind() {
        json = Bison.jsonify("[]");
        json.pair("pair", true);
        assertTrue(json.find("pair") instanceof JsonPair);
        assertTrue((Boolean)json.find("pair").value().toString().equals("true"));
    }

    /**
     * 
     */
    @Test
    public void testParse() {
        json.as(JsonValue.class).parse("[1,2,3,4,5,6,true,'name':'pair']"); // array should be emptied out before parsing
        // so the length will be 8
        assertTrue(json.as(JsonArray.class).size() == 8); // has to be 8
        
        int len = json.as(JsonValue.class).parse("[]").as(JsonArray.class).size();
        assertTrue(json.as(JsonValue.class).parse("[]").as(JsonArray.class).isEmpty());
        assertTrue(json.as(JsonValue.class).parse("[1,'string']").as(JsonArray.class).size() == 2);
    }

    /**
     * 
     */
    @Test
    public void testToString() {
        json = Bison.jsonify("[]");
        assertTrue(json.toString().equals(Json.ARRAY));
        assertTrue(Bison.jsonify("[1,2,3]").toString().equals("[1,2,3]"));
    }
}
