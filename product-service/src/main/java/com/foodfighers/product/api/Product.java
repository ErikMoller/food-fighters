package com.foodfighers.product.api;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-12.
 */
public class Product {

    private String id;
    private String name;

    public Product() {
    }

    public Product(String id, String name) {
        this.id = requireNonNull(id, "id");
        this.name = requireNonNull(name, "name");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
