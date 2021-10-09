package com.home.pio.service;

import com.home.pio.component.storage.FileStorage;
import com.home.pio.component.storage.IStorage;
import com.home.pio.component.thumbnail.IThumbnail;
import com.home.pio.component.thumbnail.ThumbPreset;
import com.home.pio.entity.Image;
import com.home.pio.entity.Preset;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ThumbnailService {

    private final IThumbnail thumbnailGenerator;
    private final IStorage storage;

    public ThumbnailService(IThumbnail thumbnailGenerator, IStorage storage) {
        this.thumbnailGenerator = thumbnailGenerator;
        this.storage = storage;
    }

    public Path generate(Preset preset, Image image) throws IOException {
        Path original = storage.path(new FileStorage().setPrefix(image.prefix).setFilename(image.filename));
        if (!Files.exists(original)) {
            throw new FileNotFoundException();
        }

        ByteArrayOutputStream output = thumbnailGenerator.generate(
                original,
                new ThumbPreset()
                        .setName(preset.name)
                        .setHeight(preset.height)
                        .setWidth(preset.width)
                        .setQuality(preset.quality)
                        .setMode(preset.mode)
        );

        Path thumbPath = storage.path(
                new FileStorage()
                .setPreset(preset.name)
                .setPrefix(image.prefix)
                .setFilename(image.filename)
        );

        storage.save(thumbPath, output);
        return thumbPath;
    }

}
