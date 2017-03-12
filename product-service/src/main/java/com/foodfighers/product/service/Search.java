package com.foodfighers.product.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Created by erimol on 2017-03-12.
 */
public class Search {

    private final String query;
    private final Optional<String> filter;

    public Search(Builder builder) {
        this.query = requireNonNull(builder.query, "query");
        this.filter = Optional.ofNullable(builder.filter);
    }

    public String getQuery() {
        return query;
    }

    public Optional<String> getFilter() {
        return filter;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    public static class Builder {
        private String query;
        private String filter;

        private Builder() {
        }

        public Builder withQuery(String query) {
            this.query = query;
            return this;
        }

        public Builder withFilter(String filter) {
            this.filter = filter;
            return this;
        }

        public Search build() {
            return new Search(this);
        }
    }
}
