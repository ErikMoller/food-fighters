package com.foodfighers.product.api;

import java.util.Collection;
import java.util.Iterator;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-12.
 */
public class Products implements Iterable<Product> {

    private final Collection<Product> products;

    public Products(Collection<Product> products) {
        this.products = requireNonNull(products, "products");
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
