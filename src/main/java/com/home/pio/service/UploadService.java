package com.home.pio.service;

import com.home.pio.component.storage.*;
import com.home.pio.entity.Bucket;
import com.home.pio.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UploadService {

    private final FileService fileService;
    private final ImageService imageService;

    public Image upload(Bucket bucket, HttpServletRequest request) throws IOException {
//        ContentDisposition content = ContentDisposition.parse(request.getHeader("Content-Disposition"));
        String contentType = request.getContentType();
        FileResource fileResource = fileService.store(MimeType.valueOf(contentType), request.getInputStream());
        Image image = (new Image())
                .setBucket(bucket)
                .setPrefix(fileResource.getPrefix())
                .setFilename(fileResource.getFilename());
        return imageService.save(image);
    }
}
