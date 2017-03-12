package com.foodfighers.product.service.search;

import com.foodfighers.product.api.Product;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.springframework.stereotype.Component;

/**
 * Created by erimol on 2017-03-12.
 */
@Component
public class ProductConverter {

    HttpEntity convert(Product product) {
        String data = "{\n" +
                "    \"name\" : \"" + product.getName() + "\"\n" +
                "}";
        System.out.println(data);
        return new NStringEntity(
                data, ContentType.APPLICATION_JSON);
    }
}
