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

import com.bingzer.bison.Bison;
import com.bingzer.bison.Json;
import com.bingzer.common.KeyPair;
import com.bingzer.common.KeyPairList;
import com.bingzer.common.OnChangedListener;

/**
 *
 * @author Ricky Tobing
 */
public class Options{
    
    /**
     * The global options
     */
    public static final Options Global = new Options();
    
    /**
     * String.
     * Retrieve the API version. This is a read only name/value find
     */
    public static final String API_VERSION         = "Version";
    
    /**
     * String. Default = (")
     * Possible values = (") or ('), the API should print out the name accordingly
     */
    public static final String STRING_QUOTER       = "StringQuoter";
    
    /**
     * String. Default = (")
     * Possible values = (") or ('), the API should print out the name accordingly
     */
    public static final String DEFAULT_NAME_QUOTER = "NameQuote";
    
    /**
     * Boolean. Default = false<br/>
     * If set to true, Json will accept Infinity as a number.
     */
    public static final String ALLOWS_INFINITY     = "AllowsInfinity";
    
    /**
     * Boolean. Default = false<br/>
     * If set to true, Json will accept NaN as a number.
     */
    public static final String ALLOWS_NAN          = "AllowsNaN";
    
    /**
     * String. Default = "  " (two spaces)<br/>
     * Possible values any whitespace characters.
     */
    public static final String TAB_CHAR            = "TabChar";
    
    /**
     * String. Default = "\r\n" or "\n" according to platform<br/>
     * Possible values any whitespace characters or new line characters.
     */
    public static final String NEW_LINE_CHAR        = "NewLine";
    
    /**
     * Serializer.
     */
    public static final String SERIALIZER          = "Serializer";
    
    /**
     * The find list
     */
    KeyPairList pairList = new KeyPairList();
    java.util.List<OnChangedListener<String, Object>> listeners
            = new java.util.LinkedList<OnChangedListener<String,Object>>();
    
    
    
    /**
     * New instance of options
     */
    @SuppressWarnings("unchecked")
    private Options(){
        pairList.add(new KeyPair(ALLOWS_INFINITY, true));
        pairList.add(new KeyPair(ALLOWS_NAN, true));
        pairList.add(new KeyPair(API_VERSION, true, true)); // readonly
        pairList.add(new KeyPair(DEFAULT_NAME_QUOTER, Constants.DOUBLE_QUOTE));
        pairList.add(new KeyPair(STRING_QUOTER, Constants.DOUBLE_QUOTE));
        pairList.add(new KeyPair(TAB_CHAR, Constants.TAB));
        pairList.add(new KeyPair(NEW_LINE_CHAR, Constants.NEW_LINE));
        pairList.add(new KeyPair(SERIALIZER, "com.bingzer.bison.serializer.Objectifier"));
    }
    
    /**
     * Adds a listener
     * @param aListener 
     */
    public final void addListener(OnChangedListener<String, Object> aListener){
        if(!listeners.contains(aListener))
            listeners.add(aListener);
    }
    
    /**
     * Remove a listener
     * @param aListener 
     */
    public final void removeListener(OnChangedListener<String, Object> aListener){
        listeners.remove(aListener);
    }
    
    /**
     * Sets options
     * @param opName
     * @param value 
     */
    @SuppressWarnings("unchecked")
    public final void setOption(String opName, Object value){
        boolean found = false;
        // iterate thru list..
        // if not found.. let's add that new find
        for(int i = 0; i < pairList.size(); i++){
            if(pairList.get(i).name().equals(opName)){
                Object oldValue = pairList.get(i).value();
                
                // before we setup the valueAs.. make sure that the instance is the same..
                if(pairList.get(i).hasSameType(value)){
                    pairList.get(i).value(value);
                    // notify listener..
                    notifyListeners(pairList.get(i), oldValue);
                }
                
                // found it..
                found = true;
                break; // done..
            }
        }
        
        if(!found) pairList.add(new KeyPair(opName, value));
    }
    
    /**
     * Returns the option valueAs..
     * @param <T> 
     * @param opName
     * @param defaultValue 
     * @return 
     */
    @SuppressWarnings("unchecked")
    public final <T> T getOption(String opName, T defaultValue){
        for(int i = 0; i < pairList.size(); i++){
            if(pairList.get(i).name().equals(opName))
                return (T) pairList.get(i).value();
        }
        
        // add it..
        pairList.add(new KeyPair(opName, defaultValue)); 
        
        // return null
        return defaultValue;
    }
    
    /**
     * Returns as string
     */
    @Override
    public String toString(){
        Json json = Bison.jsonify(Json.OBJECT);
        for(KeyPair pair : pairList){
            Object value = pair.value();
            
            // -- convert/escape string/char character
            if(value instanceof CharSequence || value instanceof java.lang.Character)
                value = "'" + JsonUtil.escape("" + value) + "'";
            
            json.pair("'" + pair.name() + "'", value);
        }
        
        return json.toString();
    }
    
    /**
     * Notify all listeners
     * @param name
     * @param newValue
     * @param oldValue 
     */
    private void notifyListeners(KeyPair pair, Object oldValue){
        for(OnChangedListener<String, Object> listener : listeners){
            listener.onChanged((String)pair.name(), pair.value(), oldValue);
        }
    }
}
