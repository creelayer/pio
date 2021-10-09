package com.home.pio.service;

import com.home.pio.entity.Bucket;
import com.home.pio.repository.BucketRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BucketService {

    private final BucketRepository bucketRepository;

    public Bucket save(Bucket bucket) {
        return bucketRepository.save(bucket);
    }

    public Page<Bucket> search(Pageable pageable){
        return bucketRepository.findAll(pageable);
    }
}