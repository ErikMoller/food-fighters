package com.foodfighers.product.service.search;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;
import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by erimol on 2017-03-24.
 */
@Service
@Profile("integration-test")
public class SearchTestService implements SearchService {

    @Override
    public void store(Product product) {

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
        Product product = Product.builder().withId(ProductId.valueOf("1")).build();
        Products products = new Products(Lists.newArrayList(product));
        return products;
    }

    @Override
    public Products search(Search search) {
        return null;
    }
}
