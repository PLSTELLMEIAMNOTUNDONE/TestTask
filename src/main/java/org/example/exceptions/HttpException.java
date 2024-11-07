package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {
    public final HttpStatus HTTP_CODE;
    public final String msg;

    HttpException(HttpStatus httpCode, String msg) {
        HTTP_CODE = httpCode;
        this.msg = msg;
    }

    HttpException(HttpStatus httpCode, Exception ex) {
        HTTP_CODE = httpCode;
        this.msg = ex.getMessage();
    }
}
