package com.home.pio.component.storage;

import java.util.UUID;

public class UuidNameGenerator implements INameGenerator {

    public static final int PREFIX_ITERATION = 3;

    private final UUID uuid = UUID.randomUUID();

    private final String filename;

    public UuidNameGenerator(String filename) {
        this.filename = filename;
    }

    @Override
    public String getFilename() {
        if (filename == null) {
            throw new IllegalStateException("Original file name not set");
        }
        return filename.replaceAll("^.+(\\.[a-z]{3,4})$", uuid + "$1");
    }

    @Override
    public String getPrefix() {
        if (filename == null) {
            throw new IllegalStateException("Original file name not set");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < PREFIX_ITERATION; i++) {
            char c = uuid.toString().charAt(i);
            if (i > 0) sb.append(java.io.File.separator);
            sb.append(c);
        }
        return sb.toString();
    }
}
