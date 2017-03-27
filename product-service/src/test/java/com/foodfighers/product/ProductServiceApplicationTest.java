package com.foodfighers.product;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;
import com.foodfighers.product.service.search.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.foodfighers.product.api.ProductTestBuilder.aProduct;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
public class ProductServiceApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SearchService searchService;

    @Before
    public void beforeMethod() {
        searchService.clear();
    }

    @Test
    public void getAllProducts() {
        Product product = aProduct().build();

        String id = restTemplate.postForObject("/v1/product", product, String.class);
        assertNotNull(id);
        Products products = restTemplate.getForObject("/v1/product", Products.class);

        assertThat(products.getProducts(), hasSize(1));
    }

    @Test
    public void getProduct() {
        Product product = aProduct().build();

        restTemplate.postForObject("/v1/product",product,String.class);
        Products products = restTemplate.getForObject("/v1/product", Products.class);

        ProductId productId = products.getProducts().get(0).getId();

        Product firstProduct = restTemplate.getForObject("/v1/product/"+productId, Product.class);
        assertNotNull(firstProduct);
    }

    @Test
    public void deleteProduct() {
        Product product = aProduct().build();

        restTemplate.postForObject("/v1/product",product,String.class);
        Products products = restTemplate.getForObject("/v1/product", Products.class);

        ProductId productId = products.getProducts().get(0).getId();

        restTemplate.delete("/v1/product/"+productId);
        products = restTemplate.getForObject("/v1/product", Products.class);

        assertThat(products.getProducts(), hasSize(0));
    }

    @Test
    public void searchProduct() {
        Product product = aProduct().build();

        restTemplate.postForObject("/v1/product",product,String.class);

        Map<String,String> queryStrings = new HashMap<>();
        queryStrings.put("q","query");
        queryStrings.put("f","filter");
        Products products = restTemplate.getForObject("/v1/product/search?q={q}&f={f}",Products.class,queryStrings);
        assertThat(products.getProducts(), hasSize(1));
    }

    @Test
    public void clear() {
        Product product = aProduct().build();

        restTemplate.postForObject("/v1/product",product,String.class);

        restTemplate.getForObject("/v1/product/clear",Void.class);

        Products products = restTemplate.getForObject("/v1/product", Products.class);

        assertThat(products.size(), equalTo(0));
    }

    @Test
    public void storeAndReadImage() throws IOException {
        ClassPathResource image = imageResource();
        long imageSize = image.contentLength();
        String imageId = "12412";

        restTemplate.postForObject("/v1/image/upload", createHttpEntityRequest(image, imageId), String.class);

        byte[] actualImage = restTemplate.getForObject("/v1/image/"+imageId, byte[].class);
        assertEquals(imageSize,actualImage.length);
    }

    private HttpEntity<Object> createHttpEntityRequest(ClassPathResource image, String imageId) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("image",image);
        map.add("id", imageId);
        map.add("name", "imageName");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        return new HttpEntity<>(map, headers);
    }

    private ClassPathResource imageResource() {
        return new ClassPathResource("axa_gold_fuit.jpg");
    }
}
