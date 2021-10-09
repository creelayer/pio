package com.home.pio.component.storage;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class FileStorage {

    public static final String ORIGIN_PATH = "origin";
    public static final String CACHE_PATH = "cache";

    private String filename;
    private String prefix;
    private String preset;
}
