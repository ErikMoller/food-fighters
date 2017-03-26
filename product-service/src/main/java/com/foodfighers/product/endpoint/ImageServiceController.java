package com.foodfighers.product.endpoint;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by erimol on 2017-03-26.
 */
@RestController("image-service")
@RequestMapping("v1/image")
public class ImageServiceController {

    @GetMapping(value = "{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void get(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        InputStream resourceAsStream = ImageServiceController.class.getClassLoader().getResourceAsStream("static/axa_gold_fuit.jpg");
        IOUtils.copy(resourceAsStream,response.getOutputStream());
    }

    //  curl -F file=@axa_gold_fuit.jpg localhost:8080/v1/image/upload
    @PostMapping(value = "upload")
    public String post(@RequestParam(value = "file", required = false) MultipartFile file,
                       @RequestParam(value = "id", required = false) String fileName) throws IOException {
        System.out.println(fileName);
        System.out.println(file.getSize());
        return "ok";
    }
}
