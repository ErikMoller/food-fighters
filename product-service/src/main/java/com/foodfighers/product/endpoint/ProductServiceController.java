package com.foodfighers.product.endpoint;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.Products;
import com.foodfighers.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by erimol on 2017-03-11.
 */
@RestController("product-service")
@RequestMapping("v1/product")
public class ProductServiceController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = GET)
    public Products getAllProducts() {
        return productRepository.readAll();
    }

    @RequestMapping(value = "{id}", method = GET)
    public Product getProduct(@PathVariable("id") String id) {
        return productRepository.read(id);
    }

    //curl -H "Content-Type: application/json" -X POST -d '{"id":"2","name":"banana"}' http://localhost:8080/v1/product
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void insertProduct(@RequestBody Product product) {
        productRepository.store(product);
    }

    //curl -X DELETE http://localhost:8080/v1/product/1
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") String id) {
        productRepository.delete(id);
    }


}