package com.foodfighers.product.api;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-15.
 */
public class NutritionFact {

    private String name;
    private Measurement measurement;
    private Unit unit;

    public NutritionFact() {
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
