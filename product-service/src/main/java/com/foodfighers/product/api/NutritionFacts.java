package com.foodfighers.product.api;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-15.
 */
public class NutritionFacts {

    private List<NutritionFact> nutritionFacts;

    public NutritionFacts() {
    }

    public NutritionFacts(List<NutritionFact> nutritionFacts) {
        this.nutritionFacts = requireNonNull(nutritionFacts, "nutritionFacts");
    }

    public List<NutritionFact> getNutritionFacts() {
        return nutritionFacts;
    }

    public void setNutritionFacts(List<NutritionFact> nutritionFacts) {
        this.nutritionFacts = nutritionFacts;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
