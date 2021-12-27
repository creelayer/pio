package com.home.pio.service;

import com.home.pio.component.storage.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


@Service
public class FileService {


    private final NameGenerator nameGenerator;
    private final Storage storage;
    private final Storage storageCache;

    public FileService(NameGenerator nameGenerator, Storage storage, @Qualifier("storage.cache") Storage storageCache){
        this.storage = storage;
        this.storageCache = storageCache;
        this.nameGenerator = nameGenerator;
    }

    public FileResource store(MimeType mimeType, InputStream inputStream) throws StorageException {
       return storage.save(nameGenerator.generateFilename(mimeType), inputStream);
    }

    public FileResource cache(String fileName, ByteArrayOutputStream outputStream) throws StorageException {
        return storageCache.save(fileName, outputStream);
    }

    public FileResource getOrigin(String filename) throws FileNotFoundException {
        return storage.getByName(filename);
    }

    public FileResource getCache(String filename) throws FileNotFoundException {
        return storageCache.getByName(filename);
    }
}
