/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
