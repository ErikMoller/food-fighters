package com.foodfighers.product.endpoint;

import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;
import com.foodfighers.product.service.search.Search;
import com.foodfighers.product.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.requireNonNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by erimol on 2017-03-11.
 */
@RestController("product-service")
@RequestMapping("v1/product")
public class ProductServiceController {

    private final SearchService searchService;

    @Autowired
    public ProductServiceController(SearchService searchService) {
        this.searchService = requireNonNull(searchService, "searchService");
    }

    @RequestMapping(method = GET)
    public Products getAll() {
        return searchService.readAll();
    }

    @RequestMapping(value = "{id}", method = GET)
    public Product get(@PathVariable("id") String id) {
        return searchService.read(ProductId.valueOf(id));
    }

    //curl -H "Content-Type: application/json" -X POST -d '{"id":"2","name":"banana"}' http://localhost:8080/v1/product
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public String insert(@RequestBody Product product) {
        return searchService.store(product).getValue();
    }

    //curl -X DELETE http://localhost:8080/v1/product/1
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        searchService.delete(ProductId.valueOf(id));
    }

    //http://localhost:8080/v1/product/search?q=query&f=filter
    @RequestMapping(value = "search", method = GET)
    public Products search(@RequestParam("q") String query, @RequestParam("f") String filter) {
        return searchService.search(Search.builder().withQuery(query).withFilter(filter).build());
    }

    @RequestMapping(value = "clear", method = GET)
    public void clear() {
        searchService.clear();
    }
}
