/*
 *  Copyright 2011 Ricky Tobing.
 *
 *  The codes provided is part of a Project Bingzer API.
 *  Free to use. Use as is since there is no warranty
 *  that code integration with your own code should always
 *  work the way they intended to do.
 *
 *  Please report any bugs to:
 *               ricky@bingzer.com
 *  Follow my blogs 
 *               http://blog.bingzer.com
 *
 */

package com.bingzer.bison.commons;

import com.bingzer.bison.JsonException;
import java.util.Date;

/**
 *
 * @author Ricky Tobing
 */
public class JsonUtil {
    
    
  
    
    /**
     * Validate json name. Names can't have fancy character in them
     * @param jsonText 
     * @return  
     */
    public static boolean isNameValid(CharSequence jsonText){
        if(jsonText.length() > 0){
            // must start with letter..
            //if(!Character.isLetter(name.charAt(0)) && name.charAt(0) != '$')
            //    return false;

            /*
            // check every character..
            boolean valid = true;
            for(int i = 0; i < name.length(); i++){
                if(i == 0 && name.charAt(0) == '$'){
                    // it's okay..
                }
                else if(Character.isDigit(name.charAt(i)) && Character.isLetter(name.charAt(i))){
                }
                else if(name.charAt(0) == '_'){
                    
                }
                
                if(!valid)
                    return false;
            }*/
        }
        
        // valid name
        return true;
    }
    
    /**
     * Check to see if <code>seq</code> is a number
     * @param jsonText 
     * @return 
     */
    public static boolean isNumber(CharSequence jsonText){
        try{
            Double.parseDouble(jsonText.toString());
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param jsonText
     * @return
     */
    public static boolean isBoolean(CharSequence jsonText){
        return StringUtil.equals(jsonText, Constants.TRUE) || StringUtil.equals(jsonText, Constants.FALSE);
    }
    
    /**
     * 
     * @param any
     * @return
     */
    public static boolean isBoolean(Object any){
        return any instanceof Boolean;
    }
    
    /**
     * 
     * @param jsonText
     * @return
     */
    public static boolean isJsonObject(CharSequence jsonText){
        return StringUtil.startsWith(jsonText, Constants.START) && 
                StringUtil.endsWith(jsonText, Constants.END);
    }
    
    /**
     * 
     * @param jsonText
     * @return
     */
    public static boolean isJsonArray(CharSequence jsonText){
        return StringUtil.startsWith(jsonText, Constants.ARRAY_START) && 
                StringUtil.endsWith(jsonText, Constants.ARRAY_END);
    }
    
    /**
     * Check to see if the supplied text can be parsed as a Date object
     * @param jsonText 
     * @return 
     */
    public static boolean isDate(CharSequence jsonText){
        return StringUtil.contains(jsonText, Constants.NEW, Constants.DATE);
    }
    
    /**
     * 
     * @param jsonText
     * @return
     */
    public static boolean isNull(CharSequence jsonText){
        return StringUtil.equals(jsonText, Constants.NULL);
    }
    
    /**
     * 
     * @param jsonText
     * @return
     */
    public static boolean isString(CharSequence jsonText){
        // must start and end either with (') or (")
        
        boolean isString = false;
        if(!isString)
            isString = (StringUtil.startsWith(jsonText, Constants.QUOTE) && StringUtil.endsWith(jsonText, Constants.QUOTE));
        if(!isString)
            isString = (StringUtil.startsWith(jsonText, Constants.DOUBLE_QUOTE) && StringUtil.endsWith(jsonText, Constants.DOUBLE_QUOTE));
        
        
        if(isString){
            // can't be confused with pairing
            // ex: 'name':'value' <= starts and ends with (')
            char ch;
            int quoteCount = 0;
            int doubleQuoteCount = 0;
            for(int i = 1; i < jsonText.length(); i++){
                ch = jsonText.charAt(i);
                if(ch == Constants.QUOTE && (i > 1 && jsonText.charAt(i - 1) != Constants.SLASH))
                    quoteCount++;
                else if(ch == Constants.DOUBLE_QUOTE && (i > 1 && jsonText.charAt(i - 1) != Constants.SLASH))
                    doubleQuoteCount++;

                if(quoteCount > 2 || doubleQuoteCount > 2){
                    isString = false;
                    break;
                }
            }
        }
        
        return isString;
    }
    
    /**
     * Example of date:
     * new Date(long)
     * @param jsonText 
     * @return 
     */
    public static Date toDate(CharSequence jsonText){
        Date date = null;
        jsonText = StringUtil.trim(jsonText);
            
        // 1. must start with new
        if(StringUtil.startsWith(jsonText, Constants.NEW)){
            // 2. Find the Date keyword
            for(int i = 0; i < jsonText.length(); i++){
                if(jsonText.charAt(i) == Constants.DATE.charAt(0) && jsonText.charAt(i - 1) == Constants.SPACE){
                    try{
                        if(jsonText.subSequence(i, (i + Constants.DATE.length())).equals(Constants.DATE)){
                            // find the date...
                            StringBuilder dateValue = new StringBuilder();
                            // find the end parentheses..
                            boolean parsing = false;
                            int j = (i + Constants.DATE.length());
                            for(; j < jsonText.length(); j++){
                                if(jsonText.charAt(j) == Constants.PARENTHESE_START || jsonText.charAt(j) == Constants.PARENTHESE_END)
                                    parsing = !parsing;
                                else if(parsing)
                                    dateValue.append(jsonText.charAt(j));
                            }// end parsing..
                            // update i
                            i = j + 1;

                            // is number..
                            if(JsonUtil.isNumber(dateValue)){
                                // take anything but later convert it to long object
                                date = new java.util.Date((long)Double.parseDouble(dateValue.toString()));
                                break;
                            }

                        }
                    }
                    catch (StringIndexOutOfBoundsException e){
                        // ignore it..
                    }
                }// end start with D
            }

        }// if starts with New
        
        if(date == null)
            // not a date!
            throw new JsonException("Unparseable date: " + jsonText);
        return date;
    }
    
    /**
     * To number
     * @param jsonText
     * @return 
     */
    public static Number toNumber(CharSequence jsonText){
        Number value = null;
        if(value == null)
            try{ value = Integer.parseInt(jsonText.toString()); }
            catch (NumberFormatException e){}
        if(value == null)
            try{ value = Long.parseLong(jsonText.toString()); }
            catch (NumberFormatException e){}
        if(value == null)
            try{ 
                value = Float.parseFloat(jsonText.toString()); 
                // if it's infinity.. we switch to double
                if((Float) value == Float.POSITIVE_INFINITY || (Float) value == Float.NEGATIVE_INFINITY)
                    value = null;
            }
            catch (NumberFormatException e){
                value = null;
            }
        if(value == null)
            try{ value = Double.parseDouble(jsonText.toString()); }
            catch (NumberFormatException e){}
        return value;
    }
    
    /**
     * Too boolean
     * @param jsonText
     * @return 
     */
    public static boolean toBoolean(CharSequence jsonText){
        return StringUtil.equals(jsonText, Constants.TRUE) ? true : false;
    }
    
    /**
     * Escape text so that escaped characters can be displayed properly
     * @param text
     * @return  
     */
    public static CharSequence escape(CharSequence text){
        CharSequence escapedText;        
        if(StringUtil.equals(text, "" + Constants.TAB)){
            escapedText = "\\t";
        }
        else if(StringUtil.equals(text, Constants.NEW_LINE))
            escapedText = "\\n";
        else{
            escapedText = new StringBuilder();
            for(int i = 0; i < text.length(); i++){
                // slash or quote
                if(text.charAt(i) == Constants.SLASH || text.charAt(i) == Constants.DOUBLE_QUOTE ||
                        text.charAt(i) == Constants.DOUBLE_QUOTE){
                    ((StringBuilder)escapedText).append(Constants.SLASH).append(text.charAt(i));
                }

                else ((StringBuilder)escapedText).append(text.charAt(i));
            }
        }
        
        return escapedText;
    }
}
