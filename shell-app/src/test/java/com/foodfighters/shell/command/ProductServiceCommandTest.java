package com.foodfighters.shell.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by erimol on 2017-03-28.
 */
public class ProductServiceCommandTest {

    @Test
    public void name() throws IOException, ParseException {
        JSONArray jsonArray = readProductsFromClasspath();
        for (Object productObject : jsonArray) {
            JSONObject product = JSONObject.class.cast(productObject);
            String jsonString = product.toJSONString();
            //System.out.println(JSONObject.class.cast(product.get("id")).get("value"));
        }
    }

    private JSONArray readProductsFromClasspath() throws IOException, ParseException {
        InputStream inputStream = ProductServiceCommandTest.class.getClassLoader().getResourceAsStream("testdata/proudcts.json");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(bufferedReader);

        return (JSONArray) jsonObject.get("products");
    }
}