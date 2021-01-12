package com.rottenpotatoes.app.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class BaseErrorResponse {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    private BaseErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    public BaseErrorResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public BaseErrorResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public BaseErrorResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus(){
        return this.status;
    }

    public void setStatus(HttpStatus status){
        this.status = status;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getDebugMessage(){
        return this.debugMessage;
    }

    public void setDebugMessage(String debugMessage){
        this.debugMessage = debugMessage;
    }

    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }

}
