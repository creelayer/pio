package com.home.pio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Page not found")
public class NotFoundHttpException extends RuntimeException {
    public NotFoundHttpException(String message){
        super(message);
    }
}
