/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingzer.bison;

import com.bingzer.common.serial.Entity;
import com.bingzer.common.serial.Property;
import com.bingzer.bison.Person.Gender;
import java.util.Date;
import org.junit.Ignore;

/**
 *
 * @author Ricky Tobing
 */
@Entity
@Ignore
public class Car {
    

    private Person owner = null;
    @Property
    private Person[] drivers = null;
    private String maker = "toyota";
    private String model = "corolla";
    private int year = 2007;

    @Property(name="colors", browsable=true)
    private Object[] stringers = {10.000334, "blue","red"};
    @Property(browsable=true, type=long.class, format="yyyy-MM-dd hh:mm:ss a")
    private long makeYear = System.currentTimeMillis();
    @Property(browsable=true, type=String.class, format="D yyyy-MM-dd hh:mm:ss a")
    private Date date = new Date(System.currentTimeMillis());

    /**
     * 
     */
    public Car(){
        owner = new Person();
        owner.setFirstName("Ricky");
        owner.setLastName("Tobing");
        owner.setGender(Gender.MALE);

        drivers = new Person[2];
        drivers[0] = owner;
        drivers[1] = new Person();
        drivers[1].setFirstName("Natalie");
        drivers[1].setLastName("Portman");
        drivers[1].setGender(Gender.FEMALE);
    }

    /**
     * 
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * 
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 
     * @return
     */
    public Person[] getDrivers() {
        return drivers;
    }

    /**
     * 
     * @param drivers
     */
    public void setDrivers(Person[] drivers) {
        this.drivers = drivers;
    }

    /**
     * 
     * @return
     */
    public long getMakeYear() {
        return makeYear;
    }

    /**
     * 
     * @param makeYear
     */
    public void setMakeYear(long makeYear) {
        this.makeYear = makeYear;
    }

    /**
     * 
     * @return
     */
    public String getMaker() {
        return maker;
    }

    /**
     * 
     * @param maker
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * 
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 
     * @return
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * 
     * @return
     */
    public Object[] getStringers() {
        return stringers;
    }

    /**
     * 
     * @param stringers
     */
    public void setStringers(Object[] stringers) {
        this.stringers = stringers;
    }

    /**
     * 
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * 
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    
}
