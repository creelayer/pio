package com.home.pio.entity;

import com.home.pio.component.thumbnail.ThumbPreset;
import com.home.pio.dto.PresetDto;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Preset {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    public UUID uuid;

    @Column(unique = true)
    public String name;

    @Column
    @Lob
    public String description;

    @Basic(optional = false)
    public short height;

    @Basic(optional = false)
    public short width;

    @Basic(optional = false)
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    public ThumbPreset.Quality quality;

    @Basic(optional = false)
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    public ThumbPreset.Mode mode;

    public Preset() {
    }

    public Preset(PresetDto dto) {
        this.name = dto.name;
        this.description = dto.description;
        this.height = dto.height;
        this.width = dto.width;
        this.quality = ThumbPreset.Quality.valueOf(dto.quality);
        this.mode = ThumbPreset.Mode.valueOf(dto.mode);
    }

}
