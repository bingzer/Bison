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

import com.bingzer.bison.Json;
import com.bingzer.bison.JsonException;

/**
 *
 * @author Ricky Tobing
 */
public interface Objectifiable {
    
    /**
     * 
     * @param <T>
     * @param json
     * @param any
     * @return
     * @throws JsonException
     */
    <T> T toObject(Json json, T any) throws JsonException;
    
    /**
     * 
     * @param object
     * @param parent
     * @return
     * @throws JsonException
     */
    Json toJson(Object object, Json parent) throws JsonException;
    
}
