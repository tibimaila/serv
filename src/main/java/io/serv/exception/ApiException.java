package io.serv.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String statusCode;

    public ApiException(HttpStatus status, String statusCode, String message) {
        super(message);
        this.status = status;
        this.statusCode = statusCode;
    }

    public HttpStatus status() {return status; }
    public String statusCode() { return statusCode; }
}
