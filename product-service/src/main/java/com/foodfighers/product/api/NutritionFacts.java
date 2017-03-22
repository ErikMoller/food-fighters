package com.foodfighers.product.api;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-15.
 */
public class NutritionFacts {

    private List<NutritionFact> nutritionFacts;

    public NutritionFacts(List<NutritionFact> nutritionFacts) {
        this.nutritionFacts = requireNonNull(nutritionFacts, "nutritionFacts");
    }

    public List<NutritionFact> getNutritionFacts() {
        return nutritionFacts;
    }
}
