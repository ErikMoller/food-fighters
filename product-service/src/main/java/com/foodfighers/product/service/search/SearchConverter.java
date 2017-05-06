package com.foodfighers.product.service.search;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;

/**
 * Created by erimol on 2017-03-14.
 */
public class SearchConverter {

    HttpEntity convert(Search search) {

        //workging
//String data = "{\n" +
//        "    \"query\": {\n" +
//        "        \"bool\" : {\n" +
//        "            \"must\" : [ \n" +
//        "                { \"match\": { \"nutritionFacts.nutritionFacts.name\" : \"Fett\" } },\n" +
//        "                { \"match\": { \"nutritionFacts.nutritionFacts.unit\" : \"G\" } }\n" +
//        "            ]\n" +
//        "         }\n" +
//        "    }\n" +
//        "}";
        //working!!1
        String data = "{\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"must\": [{\n" +
                "        \"match\": {\n" +
                "          \"nutritionFacts.nutritionFacts.unit\": \"G\"\n" +
                "        }\n" +
                "      }," +
                "                { \"match\": { \"nutritionFacts.nutritionFacts.name\" : \"Fett\" } }\n" +
                "],\n" +
                "      \"filter\": {\n" +
                "       \"range\" : {\n" +
                "            \"nutritionFacts.nutritionFacts.measurement.value\" : {\n" +
                "                \"lt\" : 490\n," +
                "                \"gt\" : 480\n" +
                "            }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
//        String data = "{\n" +
//                "  \"query\": {\n" +
//                    "\"filtered\": {" +
//                "\"query\": {" +
//                    "    \"match\": {\n" +
//                "      \"_all\": \"" + search.getQuery() + "\"\n" +
//                "    }\n" +
//                            "}}" +
//                "  }\n" +
//                "}";
//        String data = "{" +
//                " \"size\": 30," +
//                " \"from\": 0," +
//                "  \"query\": {" +
//                "    \"bool\": {" +
//                "      \"should\": {" +
//                "        \"match\": {" +
//                "          \"name\": \"" + search.getQuery() + "\"" +
//                "        }" +
//                "      }" +
//
//
//
//
////                "      \"filter\": {" +
////                "        \"term\": {" +
////                "          \"name\": \"" + search.getFilter().get() + "\"" +
////                "        }" +
////                "      }" +
//                "    }" +
//                "  }" +
//                "}";
        return new NStringEntity(
                data, ContentType.APPLICATION_JSON);
    }
}
