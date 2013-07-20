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
import com.bingzer.bison.commons.JsonUtil;

/**
 * Represents json date
 *
 * @author Ricky Tobing
 */
public class JsonDate extends JsonType<java.util.Date>{
    // <editor-fold defaultstate="collapsed" desc="Code">
    /**
     * Creates a new instance of Date
     * @param json 
     */
    protected JsonDate(Json json){
        super(json);
    }

    @Override
    protected Json parse(CharSequence jsonText) throws JsonException {
        value = JsonUtil.toDate(jsonText);

        if(value == null)
            Bison.syntaxError(this, jsonText);

        // return
        return this;
    }

    /**
     * Returns as a string..
     * @param tabChar 
     * @param lineChar 
     * @return 
     */
    @Override
    public java.lang.String toString(CharSequence tabChar, CharSequence lineChar) {
        StringBuilder builder = new StringBuilder();

        builder.append(Constants.NEW).append(Constants.SPACE).append(Constants.DATE)
                .append(Constants.PARENTHESE_START)
                .append(((java.util.Date)value).getTime())
                .append(Constants.PARENTHESE_END);

        // return
        return builder.toString();
    }

    /**
     * Overrides value with date
     * @return 
     */
    @Override
    public java.util.Date value() {
        return (java.util.Date) value;
    }

    //</editor-fold>
}
