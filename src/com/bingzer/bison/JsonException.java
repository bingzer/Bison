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

/**
 * This is an unchecked exception that runs on Runtime
 *
 * @author Ricky Tobing
 */
public class JsonException extends RuntimeException{
    
    /**
     * 
     */
    public JsonException(){
        this((String)null);
    }
    
    /**
     * 
     * @param message
     */
    public JsonException(CharSequence message){
        this(message.toString());
    }
    
    /**
     * 
     * @param message
     */
    public JsonException(String message){
        this(message, (Throwable) null);
    }
    
    /**
     * 
     * @param any
     */
    public JsonException(Throwable any){
        this(null, any);
    }
    
    /**
     * 
     * @param message
     * @param any
     */
    public JsonException(String message, Throwable any){
        super(message);
        initCause(any);
    }
}
