package com.home.pio.service;

import com.home.pio.component.storage.FileResource;
import com.home.pio.component.thumbnail.ThumbnailGenerator;
import com.home.pio.component.thumbnail.ThumbPreset;
import com.home.pio.entity.Preset;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import javax.persistence.EntityNotFoundException;
import java.io.*;

@Service
public class ThumbnailService {

    private final ThumbnailGenerator thumbnailGenerator;
    private final PresetService presetService;
    private final FileService fileService;

    public ThumbnailService(ThumbnailGenerator thumbnailGenerator, PresetService presetService, FileService fileService) {
        this.thumbnailGenerator = thumbnailGenerator;
        this.presetService = presetService;
        this.fileService = fileService;
    }

    public FileResource generate(String presetName, String filename) throws FileNotFoundException {
        try {
            return fileService.getCache(filename);
        } catch (FileNotFoundException e) {
            FileResource fileResource = fileService.getOrigin(filename);
            Preset preset = presetService.findOne(presetName).orElseThrow(EntityNotFoundException::new);
            ThumbPreset thumbPreset = new ThumbPreset()
                    .setHeight(preset.height)
                    .setWidth(preset.width)
                    .setQuality(preset.quality)
                    .setMode(preset.mode);

            ByteArrayOutputStream outputStream = thumbnailGenerator.generate(thumbPreset, MimeType.valueOf(fileResource.getMimeType()), fileResource.getInputStream());
            return fileService.cache(fileResource.getFilename(), outputStream);
        }
    }

}
