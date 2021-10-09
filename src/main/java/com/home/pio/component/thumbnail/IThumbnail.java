package com.home.pio.component.thumbnail;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public interface IThumbnail  {
    ByteArrayOutputStream generate(Path origin, ThumbPreset preset) throws IOException;
}
