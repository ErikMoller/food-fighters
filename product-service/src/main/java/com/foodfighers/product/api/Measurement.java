package com.foodfighers.product.api;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-15.
 */
public class Measurement {

    private double value;

    private Measurement(double value) {
        this.value = requireNonNull(value, "value");
    }

    public static Measurement valueOf(String value) {
        return new Measurement(Double.valueOf(value));
    }

    public double getValue() {
        return value;
    }
}
