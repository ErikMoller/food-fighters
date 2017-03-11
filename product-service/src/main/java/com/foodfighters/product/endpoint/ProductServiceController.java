package com.foodfighters.product.endpoint;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by erimol on 2017-03-11.
 */
@RestController("product-service")
@RequestMapping("v1/product")
public class ProductServiceController {

    @RequestMapping(method = GET)
    public String getAllProducts() {
        return "list all products";
    }

    @RequestMapping(value = "{id}", method = GET)
    public String getProduct(@PathVariable("id") String id) {
        return "read for id " + id;
    }
}
