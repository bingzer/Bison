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

package com.bingzer.bison.serializer;

import com.bingzer.bison.JsonException;

/**
 *
 * @author Ricky Tobing
 */
public interface Jsonifiable {
    
    /**
     * Returns all the names..
     * @return 
     */
    CharSequence[] names();
    
    /**
     * Returns the type
     * @param name
     * @return 
     */
    Class<?> typeof(CharSequence name);
    
    /**
     * Returns the value
     * @param name
     * @param value
     * @throws JsonException 
     */
    void value(CharSequence name, Object value) throws JsonException;
    
    /**
     * Returns the value
     * @param name
     * @return
     * @throws JsonException 
     */
    Object value(CharSequence name) throws JsonException;
    
}
