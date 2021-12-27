package com.home.pio.component.storage;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public interface Storage {
    FileLocalResource save(String originName, InputStream stream) throws StorageException;

    FileLocalResource save(String originName, ByteArrayOutputStream stream) throws StorageException;

    FileLocalResource getByName(String filename) throws FileNotFoundException;
}
