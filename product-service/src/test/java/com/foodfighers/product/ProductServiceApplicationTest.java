package com.foodfighers.product;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
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

import java.util.HashMap;
import java.util.Map;

import static com.foodfighers.product.api.ProductTestBuilder.aProduct;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
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

    @Test
    public void getProduct() {
        Product product = aProduct().build();

        restTemplate.postForObject("/v1/product",product,Void.class);
        Products products = restTemplate.getForObject("/v1/product", Products.class);

        ProductId productId = products.getProducts().get(0).getId();

        Product firstProduct = restTemplate.getForObject("/v1/product/"+productId, Product.class);
        assertNotNull(firstProduct);
    }

    @Test
    public void deleteProduct() {
        Product product = aProduct().build();

        restTemplate.postForObject("/v1/product",product,Void.class);
        Products products = restTemplate.getForObject("/v1/product", Products.class);

        ProductId productId = products.getProducts().get(0).getId();

        restTemplate.delete("/v1/product/"+productId);
        products = restTemplate.getForObject("/v1/product", Products.class);

        assertThat(products.getProducts(), hasSize(0));
    }

    @Test
    public void searchProduct() {
        Product product = aProduct().build();

        restTemplate.postForObject("/v1/product",product,Void.class);
        Products products = restTemplate.getForObject("/v1/product", Products.class);

        Map<String,String> queryStrings = new HashMap<>();
        queryStrings.put("q","query");
        queryStrings.put("f","filter");
        products = restTemplate.getForObject("/v1/product/search?q={q}&f={f}",Products.class,queryStrings);
        assertThat(products.getProducts(), hasSize(1));
    }
}
