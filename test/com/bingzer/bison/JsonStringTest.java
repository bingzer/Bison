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
