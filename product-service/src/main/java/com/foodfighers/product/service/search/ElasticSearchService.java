package com.foodfighers.product.service.search;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;
import org.apache.http.HttpEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-12.
 */
@Service
@Profile("default")
public class ElasticSearchService implements SearchService {

    public static final String ENDPOINT = "/products/product";
    public static final String POST = "POST";

    private final RestClient restClient;
    private final ProductConverter productConverter;
    private final SearchConverter searchConverter = new SearchConverter();

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
        try {
            Response response = restClient.performRequest("GET", ENDPOINT+"/"+id.getValue(), singletonMap("pretty", "true"));
            return productConverter.convert(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(ProductId id) {
        try {
            restClient.performRequest("DELETE",ENDPOINT+"/"+id.getValue(), emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Products readAll() {
        Response response = null;
        try {
            response = restClient.performRequest("GET", ENDPOINT+"/_search", singletonMap("pretty", "true"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productConverter.convertAll(response.getEntity());
    }

    @Override
    public Products search(Search search) {
        HttpEntity entity = searchConverter.convert(search);
        Response response = null;
        try {
            response = restClient.performRequest("GET", ENDPOINT + "/_search", singletonMap("pretty", "true"), entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productConverter.convertAll(response.getEntity());
    }
}
