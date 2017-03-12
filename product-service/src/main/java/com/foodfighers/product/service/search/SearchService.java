package com.foodfighers.product.service.search;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;

/**
 * Created by erimol on 2017-03-12.
 */
public interface SearchService {

    void store(Product product);

    Product read(ProductId id);

    void delete(ProductId id);

    Products search(Search search);
}
