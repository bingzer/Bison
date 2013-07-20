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

import com.bingzer.bison.Bison;
import com.bingzer.bison.Json;
import com.bingzer.bison.JsonArray;
import com.bingzer.bison.JsonException;
import com.bingzer.bison.JsonNull;
import com.bingzer.bison.JsonObject;
import com.bingzer.bison.JsonPair;
import com.bingzer.bison.JsonString;
import com.bingzer.bison.commons.Common;
import com.bingzer.bison.commons.Constants;
import com.bingzer.bison.commons.JsonUtil;
import com.bingzer.bison.commons.StringUtil;
import com.bingzer.common.Global;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Ricky Tobing
 */
public class Objectifier implements Objectifiable{
    
    
    /**
     * 
     * @param <T>
     * @param json
     * @param any
     * @return
     * @throws JsonException
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T toObject(Json json, T any) throws JsonException{
        
        // ---------- Jsonifiable..
        if(any instanceof Jsonifiable){
            for(CharSequence name : ((Jsonifiable)any).names()){
                Json pair = json.find(name);
                Object value = pair.value();
                if(value != null)
                    value = fromCustomType(pair, pair.value(), ((Jsonifiable)any).typeof(name), null, null);
                
                try{
                    ((Jsonifiable)any).value(name, value);
                }
                catch (Exception e){
                    throw new JsonException("Failed to instantiate object", e);
                }
            }
        }
        else{
            // populate the fields
            java.util.List<Field> fieldList = new java.util.LinkedList<Field>();
            fieldList = getAllFields(0, fieldList, any.getClass());
            for(Field field : /*object.getClass().getDeclaredFields()*/ fieldList){
                field.setAccessible(true);

                CharSequence fieldName = null;
                CharSequence format    = null;
                Class<?> customType    = null;
                boolean browsable      = true;
                // obey the Annotation if any
                if(field.getAnnotation(com.bingzer.common.serial.Property.class) != null){
                    fieldName  = field.getAnnotation(com.bingzer.common.serial.Property.class).name();
                    customType = field.getAnnotation(com.bingzer.common.serial.Property.class).type();
                    format     = field.getAnnotation(com.bingzer.common.serial.Property.class).format();
                    browsable  = field.getAnnotation(com.bingzer.common.serial.Property.class).browsable();

                    // if the field is not browsable, do not convert that spececific field
                    if(browsable){
                        // if null or empty
                        if(fieldName == null || fieldName.length() < 1) fieldName = field.getName();
                        if(format == null) format = "";

                        // TODO: if something don't match we must take care of this..
                        Json jsonValue = json.find(fieldName);
                        try{
                            // from custom type
                            Object value = fromCustomType(jsonValue, field.get(any), field.getType(), customType, format);

                            field.set(any, value);
                        }
                        catch (Exception t){
                            throw new JsonException(t.getMessage());
                        }
                    }
                }// end if

            }// end else()
        }
        
        return any;
    }
    
    /**
     * 
     * @param any
     * @param parent
     * @return
     * @throws JsonException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Json toJson(Object any, Json parent) throws JsonException{
        // -- if jsonifiable
        if(any instanceof Jsonifiable){
            for(CharSequence name : ((Jsonifiable)any).names()){
                Json json = Bison.jsonify(parent, Json.PAIR);
                json.as(JsonPair.class).name(name);
                json.value(toCustomType(json, ((Jsonifiable)any).value(name), null, null));
                ((java.util.List<Json>)parent.value()).add(json);
            }
        }
        else{
            java.util.List<Field> fieldList = new java.util.LinkedList<Field>();
            fieldList = getAllFields(0, fieldList, any.getClass());
            for(Field field : /*any.getClass().getDeclaredFields()*/ fieldList){
                field.setAccessible(true);

                CharSequence fieldName = null;
                Object fieldValue      = null;
                Class<?> customType    = null;
                String format          = null;
                boolean browsable      = true;

                // -- look for Property.class
                if(field.getAnnotation(com.bingzer.common.serial.Property.class) != null){
                    browsable      = field.getAnnotation(com.bingzer.common.serial.Property.class).browsable();
                    fieldName      = field.getAnnotation(com.bingzer.common.serial.Property.class).name();
                    customType     = field.getAnnotation(com.bingzer.common.serial.Property.class).type();
                    format         = field.getAnnotation(com.bingzer.common.serial.Property.class).format();

                    // add pairing..
                    try{
                        // only if field browsable..
                        // otherwise.. we ignore this..
                        if(browsable){
                            if(fieldName == null || fieldName.length() < 1) fieldName = field.getName();
                            fieldValue = field.get(any);

                            // -- prevent recursive..
                            if(fieldValue != any){
                                // convert to custom type if requires
                                Json json = Bison.jsonify(parent, Json.PAIR); // create find
                                json.as(JsonPair.class).name(fieldName);

                                //json.valueAs(Json.valueOf(json, fieldValue));
                                fieldValue = toCustomType(json, fieldValue, customType, format);
                                json.value(Bison.jsonify(json, fieldValue));

                                // add to parent..
                                ((java.util.List<Json>)parent.value()).add(json);
                            }
                        }
                    }
                    catch (Exception e){
                        throw new JsonException(e);
                    }
                }
            }// end if
        }
        
        return parent;
    }
    
    @SuppressWarnings("unchecked")
    private Object toCustomType(Json parent, Object value, Class<?> cl, String format){
        
        // default valueAs for this type is Class.class
        // if it's Class.class type we just ignore it and move one
        if(value != null && cl != Class.class){
            // -- we basically can conver anything to string..
            if(cl == String.class){
                // -- if valueAs is an array..
                if(value.getClass().isArray()){
                    value = Common.toObjectArray(value);
                    Object[] values = Common.toObjectArray(value);
                    // check to see if we have formatted string..
                    if(format.length() > 0){
                        value = new StringBuilder().append(String.format(format, values));
                    }
                    // regular format..
                    else{
                        value = new StringBuilder();
                        for(int i = 0; i < values.length; i++){
                            ((StringBuilder)value).append(values[i]);
                            if(i != values.length -1)
                                ((StringBuilder)value).append(Constants.COMMA);
                        }
                    }
                }
                // -- not an array..
                else{
                    // check to see if we have formatted string..
                    if(format.length() > 0){
                        if(value.getClass() == Date.class){
                            // we use SimpleDateFormatter
                            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
                            value = formatter.format((Date)value);
                        }
                        else
                            value = new StringBuilder().append(String.format(format, value));
                    }
                    else
                        value = new StringBuilder().append(value);
                }
            }
            // -- Must match to 'true' otherwise it's false..
            else if(cl == Boolean.class){
                // if valueAs is a number (1 = true, 0 = false)
                if(Common.isNumberClass(value.getClass())){
                    value = JsonUtil.toNumber(value.toString()).intValue() == 1 ? true : false;
                }
                else
                    value = value.toString().equals(Constants.TRUE);
            }
            // -- If is number
            else if(Common.isNumberClass(cl)){
                if(value.getClass() == Boolean.class)
                    value = ((Boolean) value) ? 1 : 0;
                else if(value.getClass() == String.class){
                    if(JsonUtil.isNumber((CharSequence)value)){
                        value = JsonUtil.toNumber(value.toString());
                    }
                }
                else if(value.getClass() == Date.class){
                    value = ((Date)value).getTime();
                }
            }
            // --- If it's a date
            else if(cl == Date.class){
                if(Common.isNumberClass(value.getClass())){
                    // convert to date..
                    value = new java.util.Date(JsonUtil.toNumber(value.toString()).longValue());
                }
                else if(value.getClass() == String.class){
                    // let java parses this..
                    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
                    try{
                        value = formatter.parse(value.toString());
                    }
                    catch (java.text.ParseException pe){
                        throw new JsonException(pe);
                    }
                }
            }
        }
        
        
        // ------------------------ last chance for string.formatter -----/        
        // if field valueAs is an array..
        if(value != null){
            if(value.getClass().isArray()){
                Object[] values = null;
                if(value.getClass().getComponentType().isPrimitive())
                    values = (Object[]) Global.toObjectArray(value);
                else values = (Object[]) value;
                
                JsonArray jsonArray = (JsonArray) Bison.jsonify(parent, Json.ARRAY);
                for(Object v : values){
                    // if v nul...
                    // we still need create the default object if any
                    if(v != null && v.getClass() == String.class){
                        // surround by quotes..nceof Character){
                        // check for escape character on fieldValue
                        if(v.toString().length() > 0 && v.toString().charAt(0) == Constants.SLASH)
                            v = new StringBuilder().append(v).append(Constants.SLASH);

                        v = new StringBuilder().append(Constants.DOUBLE_QUOTE)
                                .append(v).append(Constants.DOUBLE_QUOTE);
                    }

                    //((java.util.List<Json>)jsonArray.valueAs()).add(Json.valueOf(jsonArray, v));
                    if(v != null)
                        ((java.util.List<Json>)jsonArray.value()).add(Bison.jsonify(jsonArray, v));
                }

                // convert json array to string..
                value = jsonArray.toString();
            }
            else{
                // -- if the fieldValue is instanceof CharSequence we need to append (")
                if(value instanceof CharSequence || value instanceof Character){
                    // check for escape character on fieldValue
                    if(value.toString().length() > 0 && value.toString().charAt(0) == Constants.SLASH)
                        value = new StringBuilder().append(value).append(Constants.SLASH);

                    value = new StringBuilder().append(Constants.DOUBLE_QUOTE)
                            .append(value).append(Constants.DOUBLE_QUOTE);
                }
            }
        }
        
        // return itself
        return value;
    }
    
    
    
    /**
     * From custom type
     * @param jsonValue
     * @param instanceValue
     * @param typeof
     * @param customType
     * @param format
     * @return
     * @throws Throwable 
     */
    @SuppressWarnings("unchecked")
    private Object fromCustomType(Json jsonValue, Object instanceValue, Class<?> expectedType, Class<?> customType, CharSequence format) {
        // valueAt this time.. instanceValue has its own default values
        //Class<?> typeof = instanceValue.getClass();
        if(jsonValue == null){
            // there's something wrong with class structure
            throw new JsonException("Data structure is not consistent with Json object");
        }
        
        // check for each expected type
        // if the valueAs is nul.. why bother parsing it..
        if(jsonValue.value() instanceof JsonNull || jsonValue.value() == null){
            // -- nullify
            instanceValue = null;
        }
        else if(jsonValue.value() instanceof JsonArray){
            JsonArray array = (JsonArray) jsonValue.value();
            java.util.List<Json> values = (java.util.List<Json>)array.value();
            
            instanceValue = Array.newInstance(expectedType.getComponentType(), values.size());
            for(int i = 0; i < values.size(); i++){
                Object any = (values.get(i) instanceof JsonObject) ? values.get(i) : values.get(i).value();
                // more object!!! :(
                if(any instanceof JsonObject)
                    any = Bison.objectify(expectedType.getComponentType(), ((Json)any).toString()); 
                
                // finally set it to the array
                Array.set(instanceValue, i, any);
            }
        }
        // -- expected type is an array
        else if(expectedType.isArray()){
            // if json valu is a string..
            // we need to parse this : "first, second, third..."
            if(jsonValue.value() instanceof JsonString){
                // split by comma..
                CharSequence[] values = StringUtil.trimQuotes(jsonValue.value().toString()).toString().split(",");
                instanceValue = Array.newInstance(expectedType.getComponentType(), values.length);
                for(int i = 0; i < values.length; i++){
                    //Json jv = Json.valueOf(values[i]);
                    Json jv = Bison.jsonify(values[i]);
                    Array.set(instanceValue, i, fromCustomType(jv, values[i], expectedType.getComponentType(), null, null));
                }
            }
        }
        // --- expectecd type is a string
        else if(expectedType == String.class){
            // remove quotes..
            // jsonValue.valueAs() will double quotes the string
            instanceValue = StringUtil.trimQuotes(jsonValue.value().toString());
            // we also want to remove any formatting if any..
            // TODO:
        }
        // --- if expected type is enum
        else if(expectedType.isEnum()){
            Class<? extends Enum> enumClass = ((Enum)instanceValue).getDeclaringClass();
            String enumName = (jsonValue.value() instanceof Json) ? ((Json)jsonValue.value()).value().toString() : jsonValue.value().toString();
            instanceValue = Enum.valueOf(enumClass, enumName);
        }
        // --- integer
        else if(Common.isNumberClass(expectedType)){
            // remove quotes if custom type is a string
            if(customType == String.class)
                instanceValue =  JsonUtil.toNumber(StringUtil.trimQuotes(jsonValue.value().toString()));
            else instanceValue = JsonUtil.toNumber(jsonValue.value().toString());
        }
        // --- boolean type
        else if(expectedType == Boolean.class || expectedType == boolean.class){
            if(Common.isNumberClass(customType)){
                // is number class
                instanceValue = JsonUtil.toNumber(jsonValue.value().toString()).intValue() == 1 ? true : false;
            }
            else instanceValue = Boolean.parseBoolean(jsonValue.value().toString());
        }
        // --- date..
        else if(expectedType == Date.class){
            // check for custom type
            if(customType == String.class){
                // format this using 'format'
                try{
                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(format.toString());
                    instanceValue = (Date) dateFormat.parse(StringUtil.trimQuotes(jsonValue.value().toString()).toString());
                }
                catch (java.text.ParseException pe){
                    throw new JsonException("Failed to parse date", pe);
                }
            }
            // -- convert number/long to date
            else if(Common.isNumberClass(customType)){
                instanceValue = new Date(JsonUtil.toNumber(jsonValue.value().toString()).longValue());
            }   
            // -- regular date.class
            else instanceValue = JsonUtil.toDate(jsonValue.value().toString());
        }
        // --- any class
        else {
            try{
                instanceValue = expectedType.newInstance();
                if(jsonValue.value() instanceof Json)
                    instanceValue = toObject((Json)jsonValue.value(), instanceValue);
                else instanceValue = jsonValue.value();
            }
            catch (Exception e){
                throw new JsonException("Instantiation failed", e);
            }
        }
        
        
        // if nothing is found return itself
        return instanceValue;
    }
    
    private boolean includeSuperClass = true;
    private final int MAX = 10;
    private java.util.List<Field> getAllFields(int depth, java.util.List<Field> fieldList, Class<?> type){
        int d = depth + 1;
        fieldList.addAll(Arrays.asList(type.getDeclaredFields()));

        if(includeSuperClass && d <= MAX){
            if (type.getSuperclass() != null) {
                fieldList = getAllFields(d, fieldList, type.getSuperclass());
            }
        }

        
        
        return fieldList;
    }
}
