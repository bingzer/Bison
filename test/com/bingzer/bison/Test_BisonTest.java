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

import org.junit.Test;

/**
 *
 * @author Ricky Tobing
 */
public class Test_BisonTest {
    
    /**
     * 
     */
    public Test_BisonTest(){
        System.out.println(">>Running: Test -- Bison");
    }
    
    /**
     * 
     */
    @Test
    public void testAnything(){
        Bison.jsonify("yo:'mama'");
    }
    
    /**
     * Test
     */
    @Test
    public void testMethod(){
        
        CharSequence text = "";
        Json json = null; 
        Object o;
        
        json = Bison.jsonify(JsonValue.OBJECT);
        json.pair("Array", " 'whatever' ").find("Array").as(JsonPair.class).name("replaced");
        System.out.println(json.toString("  "));
        
        text = "{o:'a'}";
        json = Bison.jsonify(text);
        System.out.println(json);
        
        text = "{obj:{def:{e:{f:true}}}}";
        json = Bison.jsonify(text);
        System.out.println(json);
        
        //Global.option(Options.DEFAULT_NAME_QUOTER, "what"); 

        text = "obj:{date:new  Date(212343243123), empty :true, obj:{ objName:new Date(234234123123123)}} ";
        json = Bison.jsonify(text);
        System.out.println(json.toString());
        
        text = "obj:{a:{b:'true',c:1.000001,                 date:new Date(2.12343243123),d:{e:true}}}";
        json = Bison.jsonify(text);
        System.out.println(json.toString());
        
        text = "obj:{ a:true,c:true} ";
        json = Bison.jsonify(text);//.find("name", "\"string\""); 
        System.out.println(json.toString());
        
        json = Bison.jsonify(JsonValue.OBJECT);
        json.pair("string", "'value'");
        json.pair("char", 'c');
        json.pair("boolean", true);
        json.pair("double", -2.00002);
        json.pair("integer", 10);
        json.pair("date", new java.util.Date(System.currentTimeMillis()));
        json.pair("booleanChar", "false");
        json.pair("json", "{a:{b:true,c:{anotherOne:1.0}},d:'fff\\\"\\\"'}");
        json.pair("Null", null);
        json.pair("array", new String[]{"'whatever'","'trueasstring'","2"});  // array. Test this!
        json.pair("infinity", java.lang.Double.NaN);
        System.out.println(json.toString(" "));
        
        json = Bison.jsonify(json.toString()); 
        System.out.println(json.toString());
        
        json = Bison.jsonify(new Car());
        //json = Json.jsonify("drivers:[{a:1}]");
        System.out.println(json);
        
        
        Object newPerson = Bison.objectify(Car.class, json.toString());
        System.out.println(newPerson);
        
        
        o = new GrandParent();
        ((Person)o).setFirstName("Ricky");
        json = Bison.jsonify(o);
        System.out.println(json);
        
        o = Bison.objectify(GrandParent.class, json);
        json = Bison.jsonify(o);
        System.out.println(json);
        
        Person p = new Person();
        p.favNumbers = new int[]{1,2,3,4,5,6,7};
        o = new Car();
        ((Car)o).setOwner(p);
        
        json = Bison.jsonify(o);
        System.out.println(json.toString());
        o = Bison.objectify(Car.class, json.toString());
        System.out.println(Bison.stringify(o, "   "));
        
        System.out.println(json);
        
        json = Bison.jsonify("{}");
        json.pair("USGov", "{}").find("USGov")
            .append("executive:{president:'Barrack Obama',vice:'Joe Biden'}").find("executive")
                .append("agencies:['DOD','DEA']").find("agencies")
                    .append("'CIA'").parent("executive")
                .append("budget:10000", "location:'DC'")
                .pair("election", "{}").find("election")
                    .pair("term", "'4 years'")
                    .append("by:'the people'").parent("USGov")
            .append("legislative:{}").find("legislative")
                .append("senators:['Harry Reid']").find("senators")
                    .append("'Richard Blumenthal'", "'John McCain'")
                    .append("'Mark Warner'").parent()
                .append("congressperson:[]").find("congressperson")
                    .append("'Frank Wolf'").root().find("USGov")
            .append("judicial:{}").find("judicial")
                .append("'Chief Justice':'John Roberts'")
                .pair("Associate Justices","[]").find("Associate Justices")
                    .append("'Samuel Alit'", "'Stephen Breyer'","'Ruth Bader Ginsburg'");
                
        // oops we forget the budget for legislative
        json.find("legislative").pair("budget", "'unlimited'");       
        // oops we also forget the term for judicial members
        json.find("judicial").pair("election", "{}").find("election")
                .pair("term","'not life'");
        // we misspelled 'Samuel Alit' => supposed to be 'Samuel Alito'
        json.find("Associate Justices").valueAs(JsonArray.class).valueReplace("'Samuel Alit'", "'Samuel Alito'");
        json.find("Associate Justices").append("'Ricky Tobing'"); 
        // oops we make mistake on judicial term. it's supposed to be 'life' not 'not life'
        json.find("judicial").find("term").value("'life'");
        json.find("judicial").find("election").valueAs(JsonObject.class).pair("term", "'lsjflasdjkfsdf'");
          
        System.out.println(json.toString("  "));
        
              
    }
}
