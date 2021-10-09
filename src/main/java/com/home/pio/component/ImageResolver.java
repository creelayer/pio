package com.home.pio.component;

import com.home.pio.component.storage.FileStorage;
import com.home.pio.component.storage.IStorage;
import com.home.pio.entity.Image;
import com.home.pio.entity.Preset;
import com.home.pio.service.ImageService;
import com.home.pio.service.PresetService;
import com.home.pio.service.ThumbnailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ImageResolver {

    @Value("${upload.basePath}")
    public Path basePath;

    private final ThumbnailService thumbnailService;
    private final PresetService presetService;
    private final ImageService imageService;
    private final IStorage storage;

    public ImageResolver(ThumbnailService thumbnailService, PresetService presetService, ImageService imageService, IStorage storage) {
        this.thumbnailService = thumbnailService;
        this.presetService = presetService;
        this.imageService = imageService;
        this.storage = storage;
    }

    public FileSystemResource origin(String prefix, String filename) throws IOException {
        Path path = storage.path(new FileStorage().setPrefix(prefix).setFilename(filename));
        if (!Files.exists(path)) {
            throw new FileNotFoundException();
        }
        return new FileSystemResource(path);
    }

    public FileSystemResource thumbnail(String presetName, String prefix, String filename) throws IOException {
        Path path = storage.path(new FileStorage().setPreset(presetName).setPrefix(prefix).setFilename(filename));
        if (Files.exists(path)) {
            return new FileSystemResource(path);
        }
        Preset preset = presetService.findOne(presetName).orElseThrow(EntityNotFoundException::new);
        Image image = imageService.findOne(prefix, filename).orElseThrow(EntityNotFoundException::new);
        return new FileSystemResource(thumbnailService.generate(preset, image));
    }

}
