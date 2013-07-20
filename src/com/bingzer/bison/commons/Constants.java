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
 
package com.bingzer.bison.commons;

/**
 * Constants.
 *
 * @author Ricky Tobing
 */
public class Constants {
    /**
     * 
     */
    public static final char START            = '{';
    /**
     * 
     */
    public static final char END              = '}';
    /**
     * 
     */
    public static final char ARRAY_START      = '[';
    /**
     * 
     */
    public static final char ARRAY_END        = ']';
    /**
     * 
     */
    public static final char COMMA            = ',';
    /**
     * 
     */
    public static final char COLON            = ':';
    /**
     * 
     */
    public static final char DOUBLE_QUOTE     = '"';
    /**
     * 
     */
    public static final char QUOTE            = '\'';
    /**
     * 
     */
    public static final char SLASH            = '\\';
    /**
     * 
     */
    public static final char PARENTHESE_START = '(';
    /**
     * 
     */
    public static final char PARENTHESE_END   = ')';
    /**
     * 
     */
    public static final char SPACE            = ' ';
    /**
     * 
     */
    public static final char TAB              = '\t';
    
    // some keywords
    /**
     * 
     */
    public static final CharSequence TRUE     = "true";
    /**
     * 
     */
    public static final CharSequence FALSE    = "false";
    /**
     * 
     */
    public static final CharSequence NULL     = "null";
    /**
     * 
     */
    public static final CharSequence NEW      = "new";
    /**
     * 
     */
    public static final CharSequence DATE     = "Date";
    /**
     * 
     */
    public static final CharSequence NEW_LINE = System.getProperty("line.separator");
    /**
     * 
     */
    public static final CharSequence NONE     = "";
    /**
     * 
     */
    public static final CharSequence EMPTY    = NONE;
    
    
    
    /**
     * Json Object
     */
    public static final CharSequence OBJECT = "{}";
    
    /**
     * Json Array
     */
    public static final CharSequence ARRAY = "[]";
    
    /**
     * Pair
     */
    public static final CharSequence PAIR = EMPTY;
}
