/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingzer.bison;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Ricky Tobing
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
            com.bingzer.bison.JsonBooleanTest.class, 
            com.bingzer.bison.JsonDateTest.class, 
            com.bingzer.bison.JsonNumberTest.class, 
            com.bingzer.bison.JsonNullTest.class, 
            com.bingzer.bison.JsonStringTest.class,
            
            com.bingzer.bison.JsonArrayTest.class, 
            com.bingzer.bison.JsonTest.class, 
            com.bingzer.bison.JsonObjectTest.class, 
            com.bingzer.bison.JsonPairTest.class, 
            
            com.bingzer.bison.Test_ArrayTest.class, 
            com.bingzer.bison.Test_PrimitiveTypesTest.class, 
            com.bingzer.bison.Test_BisonTest.class
        })
public class Test_Z_All_Test {

    /**
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * 
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        System.out.println("================= Test Suite Started ================");
    }

    /**
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        System.out.println("================= Test Suite Ended ================");
    }
    
}
