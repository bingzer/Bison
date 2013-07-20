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
