package com.home.pio.component.storage;

import org.springframework.util.MimeType;

import java.util.UUID;

public class UuidNameGenerator implements NameGenerator {
    @Override
    public String generateFilename(String originFileName) {
        return originFileName.replaceAll("^.+(\\.[a-z]{3,4})$", UUID.randomUUID() + "$1");
    }

    @Override
    public String generateFilename(MimeType mimeType) {
        return UUID.randomUUID() + "." + mimeType.getSubtype();
    }
}
