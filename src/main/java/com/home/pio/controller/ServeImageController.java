package com.home.pio.controller;

import com.home.pio.component.storage.FileResource;
import com.home.pio.service.FileService;
import com.home.pio.service.ThumbnailService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@AllArgsConstructor
@RestController
@RequestMapping("files")
public class ServeImageController {

    private FileService fileService;
    private ThumbnailService thumbnailService;

    @GetMapping(value = "origin/{filename:[\\w\\-\\.]+}")
    public ResponseEntity<InputStreamResource> origin(@PathVariable String filename) throws FileNotFoundException {
            FileResource resource = fileService.getOrigin(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(resource.getMimeType()))
                    .body(new InputStreamResource(resource.getInputStream()));
    }

    @GetMapping(value = "{preset}/{filename:[\\w\\-\\.]+}")
    public ResponseEntity<InputStreamResource> thumbnail(@PathVariable String preset, @PathVariable String filename) throws FileNotFoundException {
            FileResource resource = thumbnailService.generate(preset, filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(resource.getMimeType()))
                    .body(new InputStreamResource(resource.getInputStream()));
    }
}
