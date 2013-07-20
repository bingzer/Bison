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
public class JsonNumberTest {
    
    
    Json json = null;
    CharSequence jsonText = null;
    
    /**
     * 
     */
    public JsonNumberTest() {
        System.out.println(">>Running: JsonValue.Number Test");
    }
    
    /**
     * 
     */
    @Test
    public void testInteger(){
        json = Bison.jsonify("1");
        
        // with quote
        assertTrue((Integer)json.value()== 1);
    }
    
    /**
     * 
     */
    @Test
    public void testLong(){
        json = Bison.jsonify(((long)Integer.MAX_VALUE + (long)100) + "");
        
        // with quote
        assertTrue((Long)json.value()== ((long)Integer.MAX_VALUE + 100));
    }
    
    /**
     * 
     */
    @Test
    public void testFloat(){
        json = Bison.jsonify("1.0");
        
        // with quote
        assertTrue((Float)json.value()== 1.0f);
    }
    
    /**
     * 
     */
    @Test
    public void testDouble(){
        double number = Double.MAX_VALUE;
        json = Bison.jsonify(number + "");
        
        // with quote
        assertTrue((Double)json.value()== number);
    }
    
    /**
     * 
     */
    @Test
    public void testValue(){
        json = Bison.jsonify(123445);
        assertTrue(json.as(JsonNumber.class).value() instanceof Number);
    }
}
