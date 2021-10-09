package com.home.pio.service;

import com.home.pio.component.storage.*;
import com.home.pio.entity.Bucket;
import com.home.pio.entity.Image;
import com.home.pio.service.factory.INameGeneratorFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class UploadService {

    private final ImageService imageService;
    private final IStorage storage;
    private final INameGeneratorFactory nameGeneratorFactory;

    public UploadService(ImageService imageService, IStorage storage, INameGeneratorFactory nameGeneratorFactory) {
        this.imageService = imageService;
        this.storage = storage;
        this.nameGeneratorFactory = nameGeneratorFactory;
    }

    public Image upload(Bucket bucket, MultipartFile data) throws IOException {

        INameGenerator generator = nameGeneratorFactory.create(data.getOriginalFilename());
        FileStorage file = new FileStorage();
        file.setFilename(generator.getFilename());
        file.setPrefix(generator.getPrefix());

        Image image = new Image()
                .setBucket(bucket)
                .setFilename(file.getFilename())
                .setPrefix(file.getPrefix());

        imageService.save(image);

        Path path = storage.path(new FileStorage().setPrefix(image.prefix).setFilename(image.filename));
        storage.upload(path, data);
        return image;
    }

}
