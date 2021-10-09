package com.home.pio.controller;

import com.home.pio.component.ImageResolver;
import com.home.pio.exception.NotFoundHttpException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLConnection;

@AllArgsConstructor
@RestController
@RequestMapping("files")
public class ServeImageController {

    private ImageResolver imageResolver;

    @GetMapping(value = "origin/{a}/{b}/{c}/{filename:[\\w\\-\\.]+}")
    public ResponseEntity<FileSystemResource> origin(
            @PathVariable String a,
            @PathVariable String b,
            @PathVariable String c,
            @PathVariable String filename) throws NotFoundHttpException, IOException {
        try {
            FileSystemResource resource = imageResolver.origin(a + File.separator + b + File.separator + c, filename);
            String type = URLConnection.guessContentTypeFromName(resource.getFilename());
            return ResponseEntity.ok().contentType(MediaType.valueOf(type)).body(resource);
        } catch (FileNotFoundException e) {
             throw new NotFoundHttpException("Image not found");
        }
    }

    @GetMapping(value = "{preset}/{a}/{b}/{c}/{filename:[\\w\\-\\.]+}")
    public ResponseEntity<FileSystemResource> thumbnail(
            @PathVariable String preset,
            @PathVariable String a,
            @PathVariable String b,
            @PathVariable String c,
            @PathVariable String filename) throws NotFoundHttpException, IOException {
        try {
            FileSystemResource resource = imageResolver.thumbnail(preset, a + File.separator + b + File.separator + c, filename);
            String type = URLConnection.guessContentTypeFromName(resource.getFilename());
            return ResponseEntity.ok().contentType(MediaType.valueOf(type)).body(resource);
        } catch (FileNotFoundException | EntityNotFoundException e) {
            throw new NotFoundHttpException("Image not found");
        }
    }
}
