package com.foodfighers.product.service.search;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;

/**
 * Created by erimol on 2017-03-12.
 */
public interface SearchService {

    ProductId store(Product product);

    Product read(ProductId id);

    void delete(ProductId id);

    Products readAll();

    Products search(Search search);

    void clear();
}
