package com.home.pio.dto;

import com.home.pio.component.thumbnail.ThumbPreset;
import com.home.pio.service.PresetService;
import com.home.pio.validator.Unique;
import com.home.pio.validator.ValueOfEnum;

import javax.validation.constraints.*;

public class PresetDto {

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-z0-9_]{1,50}$")
    @Unique(service = PresetService.class, fieldName = "name", message = "Preset already exist")
    public String name;

    public String description;

    @NotNull
    @Min(value = 10)
    @Max(value = 1600)
    public short height;

    @NotNull
    @Min(value = 10)
    @Max(value = 1600)
    public short width;

    @NotNull
    @ValueOfEnum(enumClass = ThumbPreset.Quality.class)
    public String quality;

    @NotNull
    @ValueOfEnum(enumClass = ThumbPreset.Mode.class)
    public String mode;

}
