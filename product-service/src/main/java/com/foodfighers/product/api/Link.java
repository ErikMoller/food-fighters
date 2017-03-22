package com.foodfighers.product.api;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-15.
 */
public class Link {

    private String value;

    private Link(String value) {
        this.value = requireNonNull(value, "value");
    }

    public static Link valueOf(String value) {
        return new Link(value);
    }

    public String getValue() {
        return value;
    }
}
