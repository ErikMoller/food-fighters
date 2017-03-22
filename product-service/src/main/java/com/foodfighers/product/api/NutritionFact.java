package com.foodfighers.product.api;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-15.
 */
public class NutritionFact {

    private String name;
    private Measurement measurement;
    private Unit unit;

    public NutritionFact(String name, Measurement measurement, Unit unit) {
        this.name = requireNonNull(name, "name");
        this.measurement = requireNonNull(measurement, "measurement");
        this.unit = requireNonNull(unit, "unit");
    }

    public String getName() {
        return name;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public Unit getUnit() {
        return unit;
    }
}
