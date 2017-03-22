package com.foodfighers.product.api;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-15.
 */
public class Ingredients {

    private List<Ingredient> ingredients;

    public Ingredients(List<Ingredient> ingredients) {
        this.ingredients = requireNonNull(ingredients, "ingredients");
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
