package com.foodfighers.product.repository;

/**
 * Created by erimol on 2017-03-27.
 */
public interface ImageRepository {

    void store(Image image);

    Image read(ImageId imageId);

}
