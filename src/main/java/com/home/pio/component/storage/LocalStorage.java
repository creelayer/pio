package com.home.pio.component.storage;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalStorage implements Storage {

    public static final int PREFIX_ITERATION = 3;

    private final Path path;

    public LocalStorage(Path path) {
        this.path = path;
    }

    @Override
    public FileLocalResource getByName(String filename) throws FileNotFoundException {
        Path fullPath = getFullPath(filename);
        if (!Files.exists(fullPath)) {
            throw new FileNotFoundException("File not found");
        }
        return new FileLocalResource(fullPath.getParent().toString(), filename, getPrefix(filename));
    }

    @Override
    public FileLocalResource save(String filename, InputStream input) throws StorageException {
        try {
            Path fullPath = getFullPath(filename);
            createStream(fullPath).write(input.readAllBytes());
            return new FileLocalResource(fullPath.getParent().toString(), filename, getPrefix(filename));
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

    @Override
    public FileLocalResource save(String filename, ByteArrayOutputStream stream) throws StorageException {
        try {
            Path fullPath = getFullPath(filename);
            stream.writeTo(createStream(fullPath));
            return new FileLocalResource(fullPath.getParent().toString(), filename, getPrefix(filename));
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

    private FileOutputStream createStream(Path fullPath) throws IOException {
        Files.createDirectories(fullPath.getParent());
        return new FileOutputStream(fullPath.toFile());
    }

    private Path getFullPath(String filename) {
        return Paths.get(path.toString(), getPrefix(filename), filename);
    }

    private String getPrefix(String filename) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < PREFIX_ITERATION; i++) {
            char c = filename.charAt(i);
            if (i > 0) sb.append(java.io.File.separator);
            sb.append(c);
        }
        return sb.toString();
    }
}
