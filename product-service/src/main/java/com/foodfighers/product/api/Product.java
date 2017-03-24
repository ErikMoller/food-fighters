package com.foodfighers.product.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-12.
 */
public class Product {

    private ProductId id;
    private String name;
    private Ingredients ingredients;
    private NutritionFacts nutritionFacts;
    private Link externalLink;

    public Product() {
    }

    public Product(ProductId id, String name) {
        this.id = requireNonNull(id, "id");
        this.name = requireNonNull(name, "name");
    }

    public Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.ingredients = builder.ingredients;
        this.nutritionFacts = builder.nutritionFacts;
        this.externalLink = builder.externalLink;
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public NutritionFacts getNutritionFacts() {
        return nutritionFacts;
    }

    public Link getExternalLink() {
        return externalLink;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ProductId id;
        private String name;
        private Ingredients ingredients;
        private NutritionFacts nutritionFacts;
        private Link externalLink;

        public Product build() {
            return new Product(this);
        }

        public Builder withId(ProductId id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withIngredients(Ingredients ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Builder withNutritionFacts(NutritionFacts nutritionFacts) {
            this.nutritionFacts = nutritionFacts;
            return this;
        }

        public Builder withExternalLink(Link externalLink) {
            this.externalLink = externalLink;
            return this;
        }
    }
}
