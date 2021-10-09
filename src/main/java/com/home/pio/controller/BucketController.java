package com.home.pio.controller;

import com.home.pio.dto.BucketDto;
import com.home.pio.dto.Response;
import com.home.pio.entity.Bucket;
import com.home.pio.service.BucketService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("v1/bucket")
public class BucketController {

    private BucketService bucketService;

    @GetMapping("")
    public Page<Bucket> index(Pageable pageable) {
        return bucketService.search(pageable);
    }

    @PostMapping("create")
    public Response<Bucket> create(@RequestBody BucketDto dto) {
        return new Response<>(bucketService.save(new Bucket(dto.name)));
    }

    @PostMapping("update/{bucket}")
    public Response<Bucket> index(@PathVariable Bucket bucket, @RequestBody BucketDto dto) {
        bucket.name = dto.name;
        return new Response<>(bucketService.save(bucket));
    }

    @GetMapping("view/{bucket}")
    public Response<Bucket> index(@PathVariable Bucket bucket) {
        return new Response<>(bucket);
    }

}
