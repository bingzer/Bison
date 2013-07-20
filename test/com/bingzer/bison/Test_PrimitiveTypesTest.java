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

import com.bingzer.bison.JsonValue;
import com.bingzer.bison.JsonType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricky Tobing
 */
public class Test_PrimitiveTypesTest {
    
    Json json;
    CharSequence jsonText;
    
    /**
     * 
     */
    public Test_PrimitiveTypesTest() {
        System.out.println(">>Running: Test -- Primitive types");
    }
    
    /**
     * 
     */
    @Test public void testByte(){
        json = Bison.jsonify((byte)1);
        assertTrue(json.as(JsonNumber.class).byteValue() == 1);
    }
    
    /**
     * 
     */
    @Test public void testShort(){
        json = Bison.jsonify((short)10);
        assertTrue(json.as(JsonNumber.class).shortValue() == 10);
    }
    
    /**
     * 
     */
    @Test public void testInteger(){
        json = Bison.jsonify((int)10);
        assertTrue(json.as(JsonNumber.class).intValue() == 10);
    }
    
    /**
     * 
     */
    @Test public void testLong(){
        json = Bison.jsonify((long)10);
        assertTrue(json.as(JsonNumber.class).longValue() == 10);
    }
    
    /**
     * 
     */
    @Test public void testFloat(){
        json = Bison.jsonify((float)10.5f);
        assertTrue((float)json.as(JsonNumber.class).floatValue() == 10.5f);
    }
    
    /**
     * 
     */
    @Test public void testDouble(){
        json = Bison.jsonify((double)10.5f);
        assertTrue((double)json.as(JsonNumber.class).doubleValue() == (double)10.5);
    }
    
    /**
     * 
     */
    @Test public void testBoolean(){
        json = Bison.jsonify(true);
        assertTrue(json.as(JsonBoolean.class).booleanValue());
    }
    
    /**
     * 
     */
    @Test public void testChar(){
        json = Bison.jsonify('c');
        assertTrue(json.as(JsonString.class).stringValue().equals("c"));
    }
}
