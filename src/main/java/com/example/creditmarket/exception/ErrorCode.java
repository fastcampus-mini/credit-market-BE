package com.example.creditmarket.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERMAIL_DUPLICATED(HttpStatus.CONFLICT, ""),
    USERMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "");

    private HttpStatus httpStatus;
    private String message;
}
