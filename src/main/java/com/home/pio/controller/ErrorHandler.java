package com.home.pio.controller;

import com.home.pio.dto.Response;
import com.home.pio.exception.NotFoundHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundHttpException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<Exception> handleCustomException(NotFoundHttpException e) {
        return Response.error(e);
    }
}
