/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingzer.bison;

import com.bingzer.bison.JsonException;
import com.bingzer.bison.serializer.Jsonifiable;
import org.junit.Ignore;

/**
 *
 * @author Ricky Tobing
 */
@Ignore
public class Guitar implements Jsonifiable{
    
    /**
     * 
     */
    public String guitarModel = null;
    /**
     * 
     */
    public String guitarMaker = null;
    /**
     * 
     */
    public int year = -1;
    

    @Override
    public void value(CharSequence name, Object value) throws JsonException {
        if(name.equals("model"))
            guitarModel = value != null ? (String)value : null;
        else if(name.equals("make"))
            guitarMaker = value != null ? (String)value : null;
        else if(name.equals("year"))
            year = (Integer) value;
    }

    @Override
    public Object value(CharSequence name) throws JsonException {
        if(name.equals("model"))
            return guitarModel;
        else if(name.equals("make"))
            return guitarMaker;
        else if(name.equals("year"))
            return year;
        return null;
    }

    @Override
    public Class<?> typeof(CharSequence name) {
        if(name.equals("year"))
            return Integer.class;
        return String.class;
    }

    @Override
    public CharSequence[] names() {
        return new String[]{"model","make","year"};
    }
    
}
