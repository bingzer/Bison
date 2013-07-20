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
