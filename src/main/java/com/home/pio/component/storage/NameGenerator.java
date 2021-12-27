package com.home.pio.component.storage;

import org.springframework.util.MimeType;

public interface NameGenerator {
    String generateFilename(String originFileName);
    String generateFilename(MimeType mimeType);
}
