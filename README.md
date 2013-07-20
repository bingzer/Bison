Bison
=====

A simple and lightweight bi-directional Java object to JSON serializer and vice versa. Notable features:

*  JSON to Java object serializer
*  Java to JSON deserializer
*  Method-chaining
*  Custom JSON parsing
*  Lightweight

The concept and purpose of this API is to be able to do something like this in Java:

    ...
    CharSequence jsonText = ...
    Object javaObject = (MyObject) ... // create your object, ANY object

    // convert to Json object
    Json json = Bison.jsonify(jsonText);

    // print the correctly-formatted json text
    System.out.println(json);

    // convert back to Java object
    javaObject = Bison.objectify(javaObject, json);
    ...
