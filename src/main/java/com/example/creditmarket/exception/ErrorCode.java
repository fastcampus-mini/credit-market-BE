package com.example.creditmarket.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERMAIL_DUPLICATED(HttpStatus.CONFLICT, ""),
    USERMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    PAGE_INDEX_ZERO(HttpStatus.BAD_REQUEST, ""),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "");


    private HttpStatus httpStatus;
    private String message;
}
