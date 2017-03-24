package com.foodfighers.product.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Created by erimol on 2017-03-12.
 */
public class Products implements Iterable<Product> {

    private List<Product> products;

    public Products() {
    }

    public Products(List<Product> products) {
        this.products = requireNonNull(products, "products");
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
