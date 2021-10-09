package com.home.pio.repository;

import com.home.pio.entity.Bucket;
import com.home.pio.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<Image, UUID> {
    Page<Image> findAllByBucket(Bucket bucket, Pageable pageable);
    Optional<Image> findByPrefixAndFilename(String prefix, String filename);
}
