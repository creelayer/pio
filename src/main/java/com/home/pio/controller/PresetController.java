package com.home.pio.controller;

import com.home.pio.component.thumbnail.ThumbPreset;
import com.home.pio.dto.PresetDto;
import com.home.pio.dto.Response;
import com.home.pio.entity.Preset;
import com.home.pio.service.PresetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("v1/preset")
public class PresetController {

    private PresetService presetService;

    @GetMapping("")
    public Response<List<Preset>> index() {
        return new Response<>(presetService.findAll());
    }

    @PostMapping("create")
    public Response<Preset> create(@Valid @RequestBody PresetDto dto) {
        return new Response<>(presetService.save(new Preset(dto)));
    }

    @PostMapping("update/{preset}")
    public Response<Preset> index(@RequestBody PresetDto dto, @PathVariable Preset preset) {
        preset.name = dto.name;
        preset.description = dto.description;
        preset.height = dto.height;
        preset.width = dto.width;
        preset.quality = ThumbPreset.Quality.valueOf(dto.quality);
        preset.mode = ThumbPreset.Mode.valueOf(dto.mode);
        return new Response<>(presetService.save(preset));
    }

    @GetMapping("view/{preset}")
    public Response<Preset> index(@PathVariable Preset preset) {
        return new Response<>(preset);
    }

}
