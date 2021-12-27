package com.home.pio.component.thumbnail;

import org.springframework.util.MimeType;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public interface ThumbnailGenerator {
    ByteArrayOutputStream generate(ThumbPreset preset, MimeType mimeType, InputStream inputStream) throws ThumbnailException;
}
