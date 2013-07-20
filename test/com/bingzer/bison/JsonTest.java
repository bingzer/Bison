/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingzer.bison;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricky Tobing
 */
public class JsonTest {
    
    Json json = null;
    CharSequence jsonText = null;
    
    /**
     * 
     */
    public JsonTest() {
        System.out.println(">>Running: Json Test");
    }
    
    /**
     * 
     */
    @Before
    public void setUp() {
        json = Bison.jsonify("{}");
        assertTrue(json instanceof JsonObject);
    }

    /**
     * 
     */
    @Test
    public void testAs() {
        json = Bison.jsonify("{}");
        assertTrue(json.as(JsonValue.class) instanceof JsonObject);
        assertTrue(json.as(JsonObject.class) instanceof JsonObject);
        json = Bison.jsonify("[]");
        assertTrue(json.as(JsonValue.class) instanceof JsonArray);
        assertTrue(json.as(JsonArray.class) instanceof JsonArray);
        json = Bison.jsonify("");
        assertTrue(json.as(JsonValue.class) instanceof JsonPair);
        assertTrue(json.as(JsonPair.class) instanceof JsonPair);
    }

    /**
     * 
     */
    @Test
    public void testRoot() {
        json = Bison.jsonify("{name:true}").find("name");
        assertTrue(json instanceof JsonPair); // <--- JsonPair
        assertTrue(json.root().parent() == null);
    }

    /**
     * 
     */
    @Test
    public void testParent_0args() {
        json = Bison.jsonify("{grandparent:{parent:{child:true}}}").find("child");
        assertTrue(json.as(JsonPair.class).name().toString().equals("child"));
        
        // parent() returns pair parent().parent() return object
        assertTrue(json.parent().parent().as(JsonPair.class).name().toString().equals("parent"));
    }

    /**
     * 
     */
    @Test
    public void testParent_CharSequence() {
        json = Bison.jsonify("{grandparent:{parent:{child:true}}}").find("child");
        assertTrue(json instanceof JsonPair); // <--- JsonPair
        assertTrue(json.as(JsonPair.class).name().toString().equals("child"));
        
        // parent up
        assertTrue(json.parent("grandparent") instanceof JsonPair);
    }

    /**
     * 
     */
    @Test
    public void testName_0args() {
        json = Bison.jsonify("x:'y'");
        assertTrue(json.as(JsonPair.class).name().toString().equals("x"));
    }

    /**
     * 
     */
    @Test
    public void testName_CharSequence() {
        json = Bison.jsonify("wrongName:'x'");
        json.as(JsonPair.class).name("name");
        assertTrue(json.toString().equals("\"name\":\"x\""));
    }

    /**
     * 
     */
    @Test
    public void testValue_Object() {
        json = Bison.jsonify("name:'value'");
        json.value(true);
        assertTrue(json.toString().equals("\"name\":true"));
    }

    /**
     * 
     */
    @Test
    public void testValue_0args() {
        json = Bison.jsonify("name:'value'");
        assertTrue(json.toString().equals("\"name\":\"value\""));
    }

    /**
     * 
     */
    @Test
    public void testValueAs() {
        json = Bison.jsonify("array:[1,2,3,4]");
        assertTrue(json.valueAs(JsonArray.class).size() == 4);
    }

    /**
     * 
     */
    @Test
    public void testToString_0args() {
        json = Bison.jsonify("array:[1,2,3,true]");
        assertTrue(json.toString().equals("\"array\":[1,2,3,true]"));
    }

    /**
     * 
     */
    @Test
    public void testRootCount() {
        json = Bison.jsonify("{array:[1,2,3,true]}").find("array");
        assertTrue(json.as(JsonValue.class).rootCount() == 1);
    }

    /**
     * 
     */
    @Test
    public void testIsBrowsable() {
        json.as(JsonValue.class).setBrowsable(true);
        assertTrue(json.as(JsonValue.class).isBrowsable());
    }

    /**
     * 
     */
    @Test
    public void testSetBrowsable() {
        json.as(JsonValue.class).setBrowsable(false);
        assertTrue(!json.as(JsonValue.class).isBrowsable());
    }

    /**
     * 
     */
    @Test
    public void testEquals() {
        Json json1 = new JsonObject(null).pair("name", true);
        Json json2 = new JsonObject(null).pair("name", true);
        assertEquals(json1, json2);
        
        json1 = new JsonArray(null).pair("name", true);
        json2 = new JsonArray(null).pair("name", true);
        assertEquals(json1, json2);
        
        json1 = new JsonPair(null).pair("name", true);
        json2 = new JsonPair(null).pair("name", true);
        assertEquals(json1, json2);
    }

    /**
     * 
     */
    @Test
    public void testHashCode() {
        Json json1 = new JsonObject(null).pair("name", true);
        Json json2 = new JsonObject(null).pair("name", true);
        assertEquals(json1.hashCode(), json2.hashCode());
        
        json1 = new JsonArray(null).pair("name", true);
        json2 = new JsonArray(null).pair("name", true);
        assertEquals(json1.hashCode(), json2.hashCode());
        
        json1 = new JsonPair(null).pair("name", true);
        json2 = new JsonPair(null).pair("name", true);
        assertEquals(json1.hashCode(), json2.hashCode());
    }

    /**
     * 
     */
    @Test
    public void testCompareTo() {        
        JsonPair json1 = new JsonPair(null).pair("name", true).find("name");
        JsonPair json2 = new JsonPair(null).pair("name", true).find("name");
        assertTrue(json1.as(JsonPair.class).compareTo(json2) == 0);
    }

    /**
     * 
     */
    @Test
    public void testJsonify_CharSequence() {
        json = Bison.jsonify("{}");
        assertTrue(json instanceof JsonObject);
        
        json = Bison.jsonify("[]");
        assertTrue(json instanceof JsonArray);
        
        json = Bison.jsonify("");
        assertTrue(json instanceof JsonPair);
    }

    /**
     * 
     */
    @Test
    public void testJsonify_Json_CharSequence() {
    }

    /**
     * 
     */
    @Test
    public void testJsonify_Object() {
    }

    /**
     * 
     */
    @Test
    public void testJsonify_Json_Object() {
    }

    /**
     * 
     */
    @Test
    public void testStringify_Object() {
    }

    /**
     * 
     */
    @Test
    public void testStringify_Object_CharSequence() {
    }

    /**
     * 
     */
    @Test
    public void testStringify_3args() {
    }

    /**
     * 
     */
    @Test
    public void testObjectify_Class_Json() {
    }

    /**
     * 
     */
    @Test
    public void testObjectify_Class_CharSequence() {
    }

    /**
     * 
     */
    @Test
    public void testObjectify_GenericType_CharSequence() {
    }

    /**
     * 
     */
    @Test
    public void testObjectify_GenericType_Json() {
    }

    /**
     * 
     */
    @Test
    public void testSyntaxError() {
    }

    /**
     * 
     */
    public class JsonImpl extends JsonValue {

        /**
         * 
         */
        public JsonImpl() {
            super(null);
        }

        public String toString(CharSequence tabChar, CharSequence lineChar) {
            return "";
        }

        protected JsonValue append(Object any) throws JsonException {
            return null;
        }

        public JsonPair find(CharSequence name) throws JsonException {
            return null;
        }

        public JsonValue pair(CharSequence name, Object any) throws JsonException {
            return null;
        }

        public JsonValue parse(CharSequence jsonText) throws JsonException {
            return null;
        }
    }
}
