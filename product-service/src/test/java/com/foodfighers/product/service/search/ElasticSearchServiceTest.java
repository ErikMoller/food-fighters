package com.foodfighers.product.service.search;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

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
    public void put() throws Exception {
        searchService.store(new Product(ProductId.valueOf("1"),"apple"));
    }

    @Test
    public void readAll() throws Exception {
        Products products = searchService.readAll();
        System.out.println(products);
    }

    @Test
    public void read() throws Exception {
        Product product = searchService.read(ProductId.valueOf("AVrEq9FLZPAroqV3da65"));
        System.out.println(product);
    }

    @Test
    public void delete() {
        searchService.delete(ProductId.valueOf("AVrEq9FLZPAroqV3da65"));
    }

    @Test
    public void search() {
        Products products = searchService.search(Search.builder().withFilter("orange").withQuery("orange").build());
        System.out.println(products);

        products = searchService.search(Search.builder().withFilter("banana").withQuery("banana").build());
        System.out.println(products);
    }

}