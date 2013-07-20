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
 *
 * @author Ricky Tobing
 */
public class StringUtil {  
    
    
    /**
     * Remove quotes
     * @param jsonText
     * @return 
     */
    public static CharSequence trimQuotes(CharSequence jsonText){
        // -- strip out 'QUOTE' or 'DOUBLE_QUOTE'
        if(startsWith((CharSequence)jsonText, Constants.QUOTE) && endsWith((CharSequence)jsonText, Constants.QUOTE))
            // strip first..
            jsonText = ((CharSequence)jsonText).subSequence(1, ((CharSequence)jsonText).length() - 1);
        else if(startsWith((CharSequence)jsonText, Constants.DOUBLE_QUOTE) && endsWith((CharSequence)jsonText, Constants.DOUBLE_QUOTE))
            // strip first..
            jsonText = ((CharSequence)jsonText).subSequence(1, ((CharSequence)jsonText).length() - 1);
        
        // return..
        return jsonText;
    }
    
    /**
     * Trim whitespace
     * @param jsonText
     * @return 
     */
    public static CharSequence trim(CharSequence jsonText){
        //return PATTERN_WHITESPACES.matcher(jsonText).replaceAll("");	
        int len = jsonText.length();
	int st = 0;
        int off = 0;

	while ((st < len) && (jsonText.charAt(off + st) <= ' '))
	    st++;
	while ((st < len) && (jsonText.charAt(off + len - 1) <= ' '))
	    len--;
	return ((st > 0) || (len < jsonText.length())) ? jsonText.subSequence(st, len) : jsonText;
    }
    
    /**
     * Compare two organics charSequence objects.
     * Because StringBuilder.equals(String) == false
     * @param text1 
     * @param text2 
     * @return 
     */
    public static boolean equals(CharSequence text1, CharSequence text2){
        if(text1 == null && text2 == null) return true;
        else if(text1 == null || text2 == null) return false;
        else{
            if(text1.length() != text2.length()) return false;

            // -- compare each character..
            for(int i = 0; i < text1.length(); i++){
                if(text1.charAt(i) != text2.charAt(i))
                    return false;
            }
        }
        
        return true;
    }
    
    /**
     * Utility method
     * @param seq
     * @param any
     * @return 
     */    
    public static boolean contains(CharSequence seq, char... any){
        for(int i = 0; i < seq.length(); i++){
            for(int j = 0; j < any.length; j++)
                if(seq.charAt(i) == any[j])
                    return true;
        }
        return false;
    }
    
    /**
     * 
     * @param seq
     * @param any
     * @return
     */
    public static boolean contains(CharSequence seq, CharSequence... any){
        for(int i = 0; i < seq.length(); i++){
            for(int j = 0; j < any.length; j++){
                // if it has the same/over any[j] length
                if(seq.length() >= any[j].length()){
                    if(seq.subSequence(0, any[j].length()).equals(any[j]))
                        return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 
     * @param seq
     * @param any
     * @return
     */
    public static boolean startsWith(CharSequence seq, char... any){
        if(seq.length() > 0){
            for(int i = 0; i < any.length; i++){
                if(seq.charAt(0) == any[i])
                    return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param seq
     * @param any
     * @return
     */
    public static boolean startsWith(CharSequence seq, CharSequence... any){
        if(seq.length() > 0){
            for(int i = 0; i < any.length; i++){
                if(seq.length() >= any[i].length())
                    if(seq.subSequence(0, any[i].length()).equals(any[i]))
                        return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param seq
     * @param any
     * @return
     */
    public static boolean endsWith(CharSequence seq, char... any){
        for(int i = 0; i < any.length; i++){
            if(seq.charAt(seq.length() - 1) == any[i])
                return true;
        }
        return false;
    }
}
