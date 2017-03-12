package com.foodfighers.product.service;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.Products;
import org.springframework.stereotype.Service;

/**
 * Created by erimol on 2017-03-12.
 */
@Service
public class ElasticSearchService implements SearchService {

    @Override
    public void store(Product product) {

    }

    @Override
    public Product read(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Products search(Search search) {
        return null;
    }
}
