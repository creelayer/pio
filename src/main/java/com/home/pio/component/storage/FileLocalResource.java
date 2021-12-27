package com.home.pio.component.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
public class FileLocalResource implements FileResource {

    private final String fullPath;
    private final String filename;
    private final String prefix;

    public FileLocalResource(String fullPath, String filename,  String prefix) {
        this.fullPath = fullPath;
        this.filename = filename;
        this.prefix = prefix;
    }

    @Override
    public String getMimeType() throws StorageException {
        try {
            return  Files.probeContentType(Path.of(fullPath + File.separator + filename));
        } catch (IOException e) {
            throw new StorageException("Cant get file mime type");
        }
    }

    @JsonIgnore
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(fullPath + File.separator + filename);
        try {
            if (inputStream.available() == 0) {
                throw new FileNotFoundException();
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
        return inputStream;
    }
}
