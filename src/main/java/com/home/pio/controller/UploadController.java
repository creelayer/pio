package com.home.pio.controller;

import com.home.pio.dto.Response;
import com.home.pio.entity.Bucket;
import com.home.pio.entity.Image;
import com.home.pio.service.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("v1/upload")
public class UploadController {

    private UploadService uploadService;

    @PostMapping("single/{bucket}")
    public Response<Image> upload(@PathVariable Bucket bucket, @RequestParam("file") MultipartFile file) throws IOException {
        return new Response<>(uploadService.upload(bucket, file));
    }
}
