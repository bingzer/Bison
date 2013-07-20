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
