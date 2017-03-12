package com.foodfighers.product.repository;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;

/**
 * Created by erimol on 2017-03-12.
 */
public interface ProductRepository {

    void store(Product product);

    Product read(ProductId id);

    Products readAll();

    void update(Product product);

    void delete(ProductId id);
}
