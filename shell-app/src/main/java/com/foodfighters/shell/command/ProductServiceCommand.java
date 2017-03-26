package com.foodfighters.shell.command;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by erimol on 2017-03-24.
 */
@Component
public class ProductServiceCommand implements CommandMarker {

    private Logger logger = Logger.getLogger(getClass());


    @Autowired private RestTemplate restTemplate;


    @CliCommand(value = "product-service data", help = "Data action for production-service")
    public String action(
            @CliOption(key = { "action" }, mandatory = true, help = "Specify [init|clear] as action") final DataAction action) throws Exception {

        switch (action) {
            case clear:
                logger.info("Clear products");
                clearProducts();
                return "Done";
            case init:
                logger.info("Init products");
                addProducts();
                return "Done";
            default:
                return "error";
        }
    }

    enum DataAction {
        clear,
        init
    }

    private void clearProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.getForObject("http://localhost:8080/v1/product/clear",Void.class);
    }

    private void addProducts() throws IOException, ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        InputStream inputStream = ProductServiceCommand.class.getClassLoader().getResourceAsStream("testdata/proudcts.json");
        System.out.println(inputStream);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(bufferedReader);

        JSONArray products = (JSONArray) jsonObject.get("products");

        logger.info("Start adding products");

        int counter = 0;
        for (Object product : products) {
            String jsonString = ((JSONObject) product).toJSONString();
            HttpEntity<Object> entity = new HttpEntity<>(jsonString,headers);
            String id = restTemplate.postForObject("http://localhost:8080/v1/product", entity, String.class);
            logger.info(String.format("Created product with id %s",id));
            counter++;
        }
        logger.info(String.format("Added %s products",counter));
    }
}
