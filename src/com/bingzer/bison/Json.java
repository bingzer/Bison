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

import com.bingzer.bison.commons.Constants;

/**
 * Represents any Json object, array, pair, values or elements. 
 * The whole API consists of two basic structural Json:
 * <code>JsonValue</code> and <code>JsonType</code>
 * 
 * <p>
 * List of Json objects that implements {@link com.bingzer.bison.JsonValue}:
 * <ul>
 *  <li>{@link com.bingzer.bison.JsonArray}</li>
 *  <li>{@link com.bingzer.bison.JsonObject}</li>
 *  <li>{@link com.bingzer.bison.JsonPair}</li>
 * </ul>
 * </p>
 * <p>
 * List of Json objects that implements{@link com.bingzer.bison.JsonType}:
 * <ul>
 *  <li>{@link com.bingzer.bison.JsonBoolean}</li>
 *  <li>{@link com.bingzer.bison.JsonDate}</li>
 *  <li>{@link com.bingzer.bison.JsonNull}</li>
 *  <li>{@link com.bingzer.bison.JsonNumber}</li>
 *  <li>{@link com.bingzer.bison.JsonString}</li>
 * </ul>
 * </p>
 * 
 * <p>
 * {@link com.bingzer.bison.Json#as(java.lang.Class) } is provided to easily help
 * programmers to dynamically change the current <code><T extends Json></code> any
 * Json.
 * </p>
 *
 * @author Ricky Tobing
 * @see com.bingzer.bison.JsonArray
 * @see com.bingzer.bison.JsonBoolean
 * @see com.bingzer.bison.JsonDate
 * @see com.bingzer.bison.JsonValue
 * @see com.bingzer.bison.JsonType
 * @see com.bingzer.bison.JsonNull
 * @see com.bingzer.bison.JsonNumber
 * @see com.bingzer.bison.JsonObject
 * @see com.bingzer.bison.JsonPair
 * @see com.bingzer.bison.JsonString
 */
public interface Json {    
    
    /**
     * Json Object.
     * Equivalent to '{}'
     */
    CharSequence OBJECT = Constants.OBJECT;
    
    /**
     * Json Array
     * Equivalent to '[]'
     */
    CharSequence ARRAY = Constants.ARRAY;
    
    /**
     * Json Pair
     * Equivalent to ''
     */
    CharSequence PAIR = Constants.PAIR;
    
    /**
     * Append 0*more <code>Object</code> to this Json.
     * <ul>
     *  <li>{@link JsonObject} will <code>append</code> to the last of its list.</li>
     *  <li>{@link JsonArray} will <code>append</code> to the last of its list.</li>
     *  <li>{@link JsonPair} will re-<code>append</code> to its value</li>
     * </ul>
     * Append method will not
     * have any effect on any subclass of <code>JsonType</code>.
     * 
     * @param any object
     * @return this (itself)
     */
    Json append(Object... any);
    
    /**
     * This method is a casting method.
     * The two examples below are equivalent:<br/>
     * <code>
     * int a = Bison.jsonify(Json.ARRAY).as(JsonArray.class).size();<br/>
     * int b = ((JsonArray)Bison.jsonify(Json.ARRAY)).size();<br/>
     * </code>
     * <p>
     * {@link Json#valueAs(java.lang.Class) } has the same concept of casting
     * the <code>Json.value</code>
     * </p>
     * 
     * @param <T> the type
     * @param jsonType the Class (any subclass of Json)
     * @return the type
     * @see Json#valueAs(java.lang.Class) 
     */
    <T extends Json> T as(Class<T> jsonType);
    
    /**
     * Returns the root of the Json tree. Root is defined as
     * any abstract Json object whose parent is null. It is
     * recommended to 'root' any Json object to print out its
     * text.
     * 
     * @return the root.
     */
    Json root();
    
    /**
     * Returns the parent of this Json object
     * 
     * @return 
     */
    Json parent();
    
    /**
     * Search thru the tree of this parent.
     * If no such parent found with such name, it should return
     * the immediate parent. name is case sensitive
     * 
     * @param name
     * @return 
     */
    Json parent(CharSequence name);
    
    /**
     * Sets/Modifies the Json 
     * 
     * @param any 
     * @return 
     */
    Json value(Object any);
    
    /**
     * Returns any Json that this Json has
     * 
     * @return Json
     */
    Object value();
    
    /**
     * Convert the following valueAs as the jsonType.
     * Be careful.
     * <p>
     * List of Json objects
     * <ul>
     * <li>{@link JsonObject}</li>
     * <li>{@link JsonArray}</li>
     * <li>{@link JsonPair}</li>
     * <li>{@link JsonNumber}</li>
     * <li>{@link JsonString}</li>
     * <li>{@link JsonBoolean}</li>
     * <li>{@link JsonDate}</li>
     * <li>{@link JsonNull}</li>
     * </ul>
     * 
     * @param <T> 
     * @param jsonType 
     * @return T 
     */
    <T extends Json> T valueAs(Class<T> jsonType);
    
    /**
     * Returns the formatted Json with tabChar
     * 
     * @param tabChar
     * @return 
     */
    String toString(CharSequence tabChar);
    
    /**
     * Returns formatted Json with new line and everything
     * 
     * @param tabChar 
     * @param lineChar 
     * @return 
     */
    String toString(CharSequence tabChar, CharSequence lineChar);
    
    /**
     * Find child/pair that has the same name
     * @param name 
     * @return 
     * @throws JsonException 
     */
    JsonPair find(CharSequence name) throws JsonException;
    
    /**
     * Adds a pair
     * @param name
     * @param any
     * @return
     * @throws JsonException 
     */
    Json pair(CharSequence name, Object any) throws JsonException;
}
