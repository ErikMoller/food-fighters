package com.foodfighers.product;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.Products;
import com.foodfighers.product.service.search.SearchTestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.foodfighers.product.api.ProductTestBuilder.aProduct;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
public class ProductServiceApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SearchTestService searchTestService;

    @Before
    public void beforeMethod() {
        searchTestService.clear();
    }

    @Test
    public void getAllProducts() {
        Product product = aProduct().build();

        restTemplate.postForObject("/v1/product",product,Void.class);
        Products products = restTemplate.getForObject("/v1/product", Products.class);

        assertThat(products.getProducts(), hasSize(1));
    }

}
