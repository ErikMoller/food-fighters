package com.foodfighers.product;

import com.foodfighers.product.api.Products;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
public class ProductServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllProducts() {
        Products products = restTemplate.getForObject("/v1/product", Products.class);
        System.out.println(products.getProducts().size());
    }

}
