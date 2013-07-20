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

import com.bingzer.common.serial.Property;
import java.util.Date;
import org.junit.Ignore;

/**
 *
 * @author Ricky Tobing
 */
@Ignore
public class Person {
    
    /**
     * 
     */
    public static enum Gender{
        /**
         * 
         */
        MALE,
        /**
         * 
         */
        FEMALE
    }
    
    
    private String firstName;
    
    @Property(browsable=true, type=String.class, format="lName=%s")
    private String lastName;
    private Gender gender = Gender.MALE;
    
    @Property(browsable=false, type=Date.class, format="yyyy-MM-dd hh:mm:ss a")
    private Date birthdate = null;
    
    /**
     * 
     */
    @Property(browsable=true)
    public int[] favNumbers = {1,2,3};
    
    /**
     * 
     */
    public Person spouse;
    
    /**
     * 
     */
    public Person(){
        
    }

    /**
     * 
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * 
     * @param gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * 
     * @param birthdate
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    
    
}
