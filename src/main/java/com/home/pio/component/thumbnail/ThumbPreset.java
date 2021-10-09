package com.home.pio.component.thumbnail;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class ThumbPreset {

    public enum Quality {
        HIGH, MEDIUM, LOW
    }

    public enum Mode {
        AUTOMATIC,
        FIT,
        FIT_TO_WIDTH,
        FIT_TO_HEIGHT
    }

    private String name;
    private short height;
    private short width;
    private Quality quality;
    private Mode mode;
}
