package com.foodfighers.product.service;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.Products;

/**
 * Created by erimol on 2017-03-12.
 */
public interface SearchService {

    void store(Product product);

    Product read(String id);

    void delete(String id);

    Products search(Search search);
}
