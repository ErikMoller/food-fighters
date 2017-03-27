package com.foodfighers.product.repository;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

/**
 * Created by erimol on 2017-03-27.
 */
public class ImageId {

    private String value;

    public ImageId() {
    }

    private ImageId(String value) {
        this.value = requireNonNull(value, "value");
    }

    public static ImageId valueOf(String id) {
        return new ImageId(id);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object rhs) {
        if (!ImageId.class.isInstance(rhs)) {
            return false;
        }
        return value.equals(ImageId.class.cast(rhs).value);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
