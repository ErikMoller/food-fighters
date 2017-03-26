package com.foodfighers.product.service.search;

import com.foodfighers.product.api.*;
import org.apache.http.HttpHost;
import org.assertj.core.util.Lists;
import org.elasticsearch.client.RestClient;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Read all...
 * http://127.0.0.1:9200/products/product/_search/?size=1000&pretty=1
 *
 *
 * Created by erimol on 2017-03-12.
 */
@Ignore
public class ElasticSearchServiceTest {

    private static ElasticSearchService searchService;

    @BeforeClass
    public static void beforeClass() {
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9201, "http")).build();
        searchService = new ElasticSearchService(restClient,new ProductConverter());
    }

    @Test
    public void testJson() {
        NutritionFact fact = new NutritionFact("name", Measurement.valueOf("10"), Unit.G);
        Ingredient ingredient1 = Ingredient.valueOf("majs");
        Ingredient ingredient2 = Ingredient.valueOf("korn");
        NutritionFacts facts = new NutritionFacts(Lists.newArrayList(fact));
        Ingredients ingredients = new Ingredients(Lists.newArrayList(ingredient1,ingredient2));
        Link link = Link.valueOf("http://mathem.se");
        Product product = Product.builder()
                .withId(null)
                .withExternalLink(link)
                .withIngredients(ingredients)
                .withNutritionFacts(facts)
                .withName("name")
                .build();
        ProductId productId = searchService.store(product);
        System.out.println("id: " + productId);
    }

    @Test
    public void clearTest() {
        searchService.clear();
    }

    @Test
    public void put() throws Exception {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(Ingredient.valueOf("havre"));
        ingredientList.add(Ingredient.valueOf("majs"));
        Ingredients ingredients = new Ingredients(ingredientList);
        searchService.store(Product.builder().withName("apple").withIngredients(ingredients).build());
    }

    @Test
    public void readAll() throws Exception {
        Products products = searchService.readAll();
        System.out.println(products);
    }

    @Test
    public void read() throws Exception {
        Product product = searchService.read(ProductId.valueOf("AVsAhxNARNa5XCKsLey9"));
        System.out.println(product);
    }

    @Test
    public void delete() {
        searchService.delete(ProductId.valueOf("AVr3aq2TX_NROvSX0h33"));
    }

    @Test
    public void search() {
        Products products = searchService.search(Search.builder().withFilter("orange").withQuery("orange").build());
        System.out.println(products);

        products = searchService.search(Search.builder().withFilter("banana").withQuery("banana").build());
        System.out.println(products);
    }

}