package com.foodfighers.product.service.search;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;
import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

/**
 * Created by erimol on 2017-03-24.
 */
@Service
@Profile("integration-test")
public class SearchTestService implements SearchService {

    private Map<ProductId,Product> products = new HashMap<>();

    @Override
    public void store(Product product) {
        ProductId id = ProductId.valueOf(UUID.randomUUID().toString());
        product.setId(id);
        products.put(id,product);
    }

    @Override
    public Product read(ProductId id) {
        return products.get(id);
    }

    @Override
    public void delete(ProductId id) {
        products.remove(id);
    }

    @Override
    public Products readAll() {
        return new Products(products.values().stream().collect(toList()));
    }

    @Override
    public Products search(Search search) {
        return new Products(products.values().stream().collect(toList()));
    }

    public void clear() {
        products.clear();
    }
}
