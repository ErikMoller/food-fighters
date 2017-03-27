package com.foodfighters.shell.command;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        JSONArray products = readProductsFromClasspath();

        logger.info("Start adding products");
        int counter = 0;
        for (Object product : products) {
            String jsonString = ((JSONObject) product).toJSONString();
            String id = submitProductToProductService(jsonString);
            logger.info(String.format("Created product with id %s",id));
            addImage(id);
            counter++;
        }
        logger.info(String.format("Added %s products",counter));
    }

    private String submitProductToProductService(String jsonString) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(jsonString,headers);
        return restTemplate.postForObject("http://localhost:8080/v1/product", entity, String.class);
    }

    private JSONArray readProductsFromClasspath() throws IOException, ParseException {
        InputStream inputStream = ProductServiceCommand.class.getClassLoader().getResourceAsStream("testdata/proudcts.json");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(bufferedReader);

        return (JSONArray) jsonObject.get("products");
    }

    private void addImage(String id) {
        ClassPathResource imageResource = imageResource(id);
        restTemplate.postForObject("http://localhost:8080/v1/image/upload", createHttpEntityRequest(imageResource, id), String.class);
        logger.info("Added image with id " + id);
    }

    private ClassPathResource imageResource(String id) {
        return new ClassPathResource("images/axa_gold_fuit.jpg");
    }

    private HttpEntity<Object> createHttpEntityRequest(ClassPathResource image, String imageId) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("image",image);
        map.add("id", imageId);
        map.add("name", "imageName");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        return new HttpEntity<>(map, headers);
    }
}
