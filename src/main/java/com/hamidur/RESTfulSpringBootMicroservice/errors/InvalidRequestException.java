package com.hamidur.RESTfulSpringBootMicroservice.errors;

public class InvalidRequestException extends RuntimeException
{
    private int status;
    private String message;

    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public InvalidRequestException(String message, int status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public InvalidRequestException(String message, Throwable cause, int status, String message1) {
        super(message, cause);
        this.status = status;
        this.message = message1;
    }

    public InvalidRequestException(Throwable cause, int status, String message) {
        super(cause);
        this.status = status;
        this.message = message;
    }

    public InvalidRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int status, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.message = message1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "InvalidRequestException{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
