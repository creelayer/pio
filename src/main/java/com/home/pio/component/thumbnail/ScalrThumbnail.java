package com.home.pio.component.thumbnail;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ScalrThumbnail implements ThumbnailGenerator {

    @Override
    public ByteArrayOutputStream generate(ThumbPreset preset, MimeType mimeType, InputStream inputStream) throws ThumbnailException {
        try {
            BufferedImage image = ImageIO.read(inputStream);

            BufferedImage buffer = Scalr.resize(
                    image,
                    method(preset.quality),
                    mode(preset.mode),
                    preset.width,
                    preset.height
            );

            ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
            ImageIO.write(buffer, mimeType.getSubtype(), thumbOutput);
            return thumbOutput;
        } catch (IOException e) {
            throw new ThumbnailException(e.getMessage());
        }
    }

    private Scalr.Method method(ThumbPreset.Quality quality) {
        if (quality.equals(ThumbPreset.Quality.LOW)) {
            return Scalr.Method.SPEED;
        } else if (quality.equals(ThumbPreset.Quality.MEDIUM)) {
            return Scalr.Method.QUALITY;
        } else if (quality.equals(ThumbPreset.Quality.HIGH)) {
            return Scalr.Method.ULTRA_QUALITY;
        }
        throw new IllegalArgumentException("Quality method not found");
    }

    private Scalr.Mode mode(ThumbPreset.Mode mode) {
        if (mode.equals(ThumbPreset.Mode.AUTOMATIC)) {
            return Scalr.Mode.AUTOMATIC;
        } else if (mode.equals(ThumbPreset.Mode.FIT)) {
            return Scalr.Mode.FIT_EXACT;
        } else if (mode.equals(ThumbPreset.Mode.FIT_TO_WIDTH)) {
            return Scalr.Mode.FIT_TO_WIDTH;
        } else if (mode.equals(ThumbPreset.Mode.FIT_TO_HEIGHT)) {
            return Scalr.Mode.FIT_TO_HEIGHT;
        }
        throw new IllegalArgumentException("Quality method not found");
    }

}
