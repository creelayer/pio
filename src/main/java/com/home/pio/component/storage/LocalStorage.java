package com.home.pio.component.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class LocalStorage implements IStorage {

    @Value("${upload.basePath}")
    public Path basePath;

    public void upload(Path path, MultipartFile uploadFile) throws IOException {
        Files.createDirectories(path.getParent());
        uploadFile.transferTo(path);
    }

    public void save(Path path, ByteArrayOutputStream stream) throws IOException {
        Files.createDirectories(path.getParent());
        stream.writeTo(new FileOutputStream(path.toFile()));
    }

    @Override
    public Path path(FileStorage file) {
        if (file.getPreset() == null) {
            return Paths.get(
                    basePath +
                            File.separator +
                            FileStorage.ORIGIN_PATH +
                            File.separator +
                            file.getPrefix() +
                            File.separator +
                            file.getFilename()
            );
        }
        return Paths.get(
                basePath +
                        File.separator +
                        FileStorage.CACHE_PATH +
                        File.separator +
                        file.getPreset() +
                        File.separator +
                        file.getPrefix() +
                        File.separator +
                        file.getFilename()
        );
    }
}
