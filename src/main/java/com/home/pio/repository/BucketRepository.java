package com.home.pio.repository;

import com.home.pio.entity.Bucket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BucketRepository extends PagingAndSortingRepository<Bucket, UUID> {
}
