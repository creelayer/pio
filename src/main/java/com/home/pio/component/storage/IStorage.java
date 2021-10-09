package com.home.pio.component.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public interface IStorage {
    void upload(Path path, MultipartFile uploadFile) throws IOException;

    void save(Path path, ByteArrayOutputStream stream) throws IOException;

    Path path(FileStorage file);
}
