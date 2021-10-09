package com.home.pio.component.thumbnail;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ScalrThumbnail implements IThumbnail {

    @Override
    public ByteArrayOutputStream generate(Path origin, ThumbPreset preset) throws IOException {
        if (!Files.exists(origin)) {
            throw new FileNotFoundException("Origin file not found.");
        }
        BufferedImage buffer = Scalr.resize(ImageIO.read(origin.toFile()),
                method(preset),
                mode(preset),
                preset.getWidth(),
                preset.getHeight());


        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
        String mimeType = Files.probeContentType(origin);
        ImageIO.write(buffer, mimeType.split("/")[1], thumbOutput);
        return thumbOutput;
    }

    private Scalr.Method method(ThumbPreset preset) {
        if (preset.getQuality().equals(ThumbPreset.Quality.LOW)) {
            return Scalr.Method.SPEED;
        } else if (preset.getQuality().equals(ThumbPreset.Quality.MEDIUM)) {
            return Scalr.Method.QUALITY;
        } else if (preset.getQuality().equals(ThumbPreset.Quality.HIGH)) {
            return Scalr.Method.ULTRA_QUALITY;
        }
        throw new IllegalArgumentException("Quality method not found");
    }

    private Scalr.Mode mode(ThumbPreset preset) {
        if (preset.getMode().equals(ThumbPreset.Mode.AUTOMATIC)) {
            return Scalr.Mode.AUTOMATIC;
        }else if (preset.getMode().equals(ThumbPreset.Mode.FIT)) {
            return Scalr.Mode.FIT_EXACT;
        } else if (preset.getMode().equals(ThumbPreset.Mode.FIT_TO_WIDTH)) {
            return Scalr.Mode.FIT_TO_WIDTH;
        } else if (preset.getMode().equals(ThumbPreset.Mode.FIT_TO_HEIGHT)) {
            return Scalr.Mode.FIT_TO_HEIGHT;
        }


        throw new IllegalArgumentException("Quality method not found");
    }

}
