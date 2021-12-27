package com.home.pio.controller;

import com.home.pio.component.storage.StorageException;
import com.home.pio.component.thumbnail.ThumbnailException;
import com.home.pio.dto.Response;
import com.home.pio.exception.NotFoundHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundHttpException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<Exception> handleCustomException(NotFoundHttpException e) {
        return Response.error(e);
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<Exception> handleCustomException(FileNotFoundException e) {
        return Response.error(e);
    }

    @ExceptionHandler(StorageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Exception> handleCustomException(StorageException e) {
        return Response.error(e);
    }

    @ExceptionHandler(ThumbnailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Exception> handleCustomException(ThumbnailException e) {
        return Response.error(e);
    }

}
