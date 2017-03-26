package com.foodfighters.shell.command;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by erimol on 2017-03-24.
 */
@Component
public class ProductServiceCommand implements CommandMarker {

    private Logger logger = Logger.getLogger(getClass());


    @Autowired private RestTemplate restTemplate;


    @CliCommand(value = "product-service data", help = "Data action for production-service")
    public String action(
            @CliOption(key = { "action" }, mandatory = true, help = "Specify [init|clear] as action") final DataAction action) {

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

    private void addProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"name\":\"apple\",\"ingredients\":{\"ingredients\":[{\"name\":\"erik\"},{\"name\":\"majs\"}]},\"nutritionFacts\":null,\"externalLink\":null}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        restTemplate.postForObject("http://localhost:8080/v1/product",entity,Void.class);
    }
}
