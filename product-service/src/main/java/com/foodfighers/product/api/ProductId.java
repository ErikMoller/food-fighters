package com.foodfighers.product.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Created by erimol on 2017-03-12.
 */
public class ProductId {

    private final String value;

    private ProductId(String value) {
        this.value = requireNonNull(value, "value");
    }

    public static ProductId valueOf(String id) {
        return new ProductId(id);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object rhs) {
        return reflectionEquals(this, rhs);

    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
