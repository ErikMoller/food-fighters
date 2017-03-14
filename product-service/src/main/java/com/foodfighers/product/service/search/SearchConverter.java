package com.foodfighers.product.service.search;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;

/**
 * Created by erimol on 2017-03-14.
 */
public class SearchConverter {

    HttpEntity convert(Search search) {
        String data = "{\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"should\": {\n" +
                "        \"match\": {\n" +
                "          \"name\": \"" + search.getQuery() + "\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"filter\": {\n" +
                "        \"term\": {\n" +
                "          \"name\": \"" + search.getFilter().get() + "\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        return new NStringEntity(
                data, ContentType.APPLICATION_JSON);
    }
}
