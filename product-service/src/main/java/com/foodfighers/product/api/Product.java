package com.foodfighers.product.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-12.
 */
public class Product {

    private ProductId id;
    private String name;

    public Product() {
    }

    public Product(ProductId id, String name) {
        this.id = requireNonNull(id, "id");
        this.name = requireNonNull(name, "name");
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
