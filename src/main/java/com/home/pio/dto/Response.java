package com.home.pio.dto;

public class Response<T> {

    public Integer code;
    public String message;
    public T content;

    public Response(T content){
        this.content = content;
    }

    public static Response<Exception> error(Exception e){
        Response<Exception> response = new Response<>(null);
        response.message = e.getMessage();
        return response;
    }

}
