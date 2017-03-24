package com.foodfighers.product.api;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-15.
 */
public class Ingredient {

    private String name;

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = requireNonNull(name, "name");
    }

    public static Ingredient valueOf(String name) {
        return new Ingredient(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
