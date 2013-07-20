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
