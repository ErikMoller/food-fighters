package com.foodfighers.product.repository;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-27.
 */
public class Image {

    private ImageId imageId;
    private String name;
    private byte[] content;

    public Image(ImageId imageId, String name, byte[] content) {
        this.imageId = requireNonNull(imageId, "imageId");
        this.name = requireNonNull(name, "name");
        this.content = requireNonNull(content, "content");
    }

    public ImageId getImageId() {
        return imageId;
    }

    public void setImageId(ImageId imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
