package com.foodfighers.product.api;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-15.
 */
public class Ingredients implements Iterable<Ingredient> {

    private List<Ingredient> ingredients;

    public Ingredients() {
    }

    public Ingredients(List<Ingredient> ingredients) {
        this.ingredients = requireNonNull(ingredients, "ingredients");
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return ingredients.iterator();
    }
}
