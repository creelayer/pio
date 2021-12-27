package com.home.pio.controller;

import com.home.pio.dto.Response;
import com.home.pio.entity.Bucket;
import com.home.pio.entity.Image;
import com.home.pio.service.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("v1/upload")
public class UploadController {

    private UploadService uploadService;

    @PostMapping(value = "single/{bucket}", consumes = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Response<Image> upload(@PathVariable Bucket bucket, HttpServletRequest request) throws IOException {
        return new Response<>(uploadService.upload(bucket, request));
    }
}
