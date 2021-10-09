package com.home.pio.controller;

import com.home.pio.entity.Bucket;
import com.home.pio.entity.Image;
import com.home.pio.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/image")
public class ImageController {

    private ImageService imageService;

    @GetMapping("{bucket}")
    public Page<Image> index(@PathVariable Bucket bucket, Pageable pageable){
        return imageService.search(bucket, pageable);
    }

}
