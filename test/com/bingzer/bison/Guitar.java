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
