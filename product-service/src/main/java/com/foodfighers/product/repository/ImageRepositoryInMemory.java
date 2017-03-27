package com.foodfighers.product.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by erimol on 2017-03-27.
 */
@Component
public class ImageRepositoryInMemory implements ImageRepository {

    private final Map<ImageId,Image> images = new HashMap<>();

    @Override
    public void store(Image image) {
        images.put(image.getImageId(),image);
    }

    @Override
    public Image read(ImageId imageId) {
        return images.get(imageId);
    }
}
