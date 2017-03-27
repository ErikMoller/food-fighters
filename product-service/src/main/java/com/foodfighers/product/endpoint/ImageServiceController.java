package com.foodfighers.product.endpoint;

import com.foodfighers.product.repository.Image;
import com.foodfighers.product.repository.ImageId;
import com.foodfighers.product.repository.ImageRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

/**
 * Created by erimol on 2017-03-26.
 */
@RestController("image-service")
@RequestMapping("v1/image")
public class ImageServiceController {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceController(ImageRepository imageRepository) {
        this.imageRepository = requireNonNull(imageRepository, "imageRepository");
    }

    @GetMapping(value = "{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void get(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        Image image = imageRepository.read(ImageId.valueOf(id));

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image.getContent())) {
            IOUtils.copy(byteArrayInputStream,response.getOutputStream());
        }
    }

    //  curl -F file=@axa_gold_fuit.jpg localhost:8080/v1/image/upload
    @PostMapping(value = "upload")
    public String post(@RequestParam(value = "image", required = false) MultipartFile file,
                       @RequestParam(value = "id", required = false) String id,
                       @RequestParam(value = "name", required = false) String name) throws IOException {
        Image image = new Image(ImageId.valueOf(id), name, file.getBytes());
        imageRepository.store(image);
        return "ok";
    }
}
