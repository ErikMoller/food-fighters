package com.foodfighers.product.service.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodfighers.product.api.Product;
import com.foodfighers.product.api.ProductId;
import com.foodfighers.product.api.Products;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Created by erimol on 2017-03-12.
 */
@Component
public class ProductConverter {

    HttpEntity convert(Product product) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(product);
        System.out.println(data);
        return new NStringEntity(
                data, ContentType.APPLICATION_JSON);
    }

    Product convert(HttpEntity httpEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseHit responseHit = null;
        try {
            responseHit = objectMapper.readValue(httpEntity.getContent(), ResponseHit.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        responseHit.source.setId(ProductId.valueOf(responseHit._id));
        return responseHit.source;
    }

    Products convertAll(HttpEntity httpEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseHits responseHits = null;
        try {
            responseHits = objectMapper.readValue(httpEntity.getContent(), ResponseHits.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseHits.hits.hits.stream()
            .map(hit -> {
                hit.source.setId(ProductId.valueOf(hit.id));
                return hit.source;
            })
            .collect(collectingAndThen(toList(),Products::new));
    }

    public static class ResponseHit {

        @JsonProperty(value = "_index")
        private String _index;

        @JsonProperty(value = "_type")
        private String _type;

        @JsonProperty(value = "_id")
        private String _id;

        @JsonProperty(value = "_version")
        private String _version;

        @JsonProperty(value = "found")
        private String found;

        @JsonProperty(value = "_source")
        private Product source;
    }

    public static class ResponseHits {

        @JsonProperty(value = "hits")
        private Hits hits;

        @JsonProperty(value = "took")
        private String took;

        @JsonProperty(value = "timed_out")
        private String timed_out;

        @JsonProperty(value = "_shards")
        private Shards shards;
    }
    public static class Hits {
        @JsonProperty(value = "total")
        private String total;

        @JsonProperty(value = "max_score")
        private String max_score;

        @JsonProperty(value = "hits")
        private List<Hit> hits;
    }
    public static class Hit {

        @JsonProperty(value = "_index")
        private String index;

        @JsonProperty(value = "_type")
        private String type;

        @JsonProperty(value = "_id")
        private String id;

        @JsonProperty(value = "_score")
        private Double score;

        @JsonProperty(value = "_source")
        private Product source;
    }

    public static class Shards {
        @JsonProperty(value = "total")
        private String total;
        @JsonProperty(value = "successful")
        private String successful;
        @JsonProperty(value = "failed")
        private String failed;

    }

    public static class Source {

        @JsonProperty(value = "name")
        private String name;
    }
}
