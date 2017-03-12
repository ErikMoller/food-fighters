package com.foodfighers.product.repository;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.Products;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by erimol on 2017-03-12.
 */
@Repository
public class ProductRepositoryInMemory implements ProductRepository {

    private final Map<String,Product> products = new HashMap<>();

    @Override
    public void store(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public Product read(String id) {
        return products.get(id);
    }

    @Override
    public Products readAll() {
        return new Products(products.values());
    }

    @Override
    public void update(Product product) {
        products.put(product.getId(), product);
    }



    @Override
    public void delete(String id) {
        products.remove(id);
    }
}