package com.home.pio.service;

import com.home.pio.entity.Bucket;
import com.home.pio.entity.Image;
import com.home.pio.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ImageService {

    private ImageRepository imageRepository;

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public Optional<Image> findOne(UUID uuid) {
        return imageRepository.findById(uuid);
    }

    public Optional<Image> findOne(String prefix, String filename) {
        return imageRepository.findByPrefixAndFilename(prefix, filename);
    }

    public Page<Image> search(Bucket bucket, Pageable pageable) {
        return imageRepository.findAllByBucket(bucket, pageable);
    }

}
