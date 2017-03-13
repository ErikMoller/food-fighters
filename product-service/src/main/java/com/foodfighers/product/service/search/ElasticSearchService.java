package com.foodfighers.product.service.search;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;
import org.apache.http.HttpEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

import static java.util.Collections.emptyMap;
import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-12.
 */
@Service
public class ElasticSearchService implements SearchService {

    public static final String ENDPOINT = "/products/product";
    public static final String POST = "POST";

    private final RestClient restClient;
    private final ProductConverter productConverter;

    @Autowired
    public ElasticSearchService(RestClient restClient, ProductConverter productConverter) {
        this.restClient = requireNonNull(restClient, "restClient");
        this.productConverter = productConverter;
    }

    @Override
    public void store(Product product) {
        try {
            HttpEntity entity = productConverter.convert(product);
            restClient.performRequest(POST,ENDPOINT,emptyMap(),entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product read(ProductId id) {
        return null;
    }

    @Override
    public void delete(ProductId id) {

    }

    @Override
    public Products readAll() {
        Response response = null;
        try {
            response = restClient.performRequest("GET", ENDPOINT+"/_search", Collections.singletonMap("pretty", "true"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productConverter.convert(response.getEntity());
    }

    @Override
    public Products search(Search search) {
        return null;
    }


}
