package com.foodfighers.product.api;

import org.assertj.core.util.Lists;

/**
 * Created by erimol on 2017-03-24.
 */
public class ProductTestBuilder {

    private ProductTestBuilder() {
    }

    public static ProductTestBuilder aProduct() {
        return new ProductTestBuilder();
    }

    public Product build() {
        NutritionFact fact = new NutritionFact("name", Measurement.valueOf("10"), Unit.G);
        Ingredient ingredient1 = Ingredient.valueOf("majs");
        Ingredient ingredient2 = Ingredient.valueOf("korn");
        NutritionFacts facts = new NutritionFacts(Lists.newArrayList(fact));
        Ingredients ingredients = new Ingredients(Lists.newArrayList(ingredient1,ingredient2));
        Link link = Link.valueOf("http://mathem.se");
        return Product.builder()
                .withId(null)
                .withExternalLink(link)
                .withIngredients(ingredients)
                .withNutritionFacts(facts)
                .withName("name")
                .build();
    }
}