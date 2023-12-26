package com.realEstate.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER not founded"),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND, "Invalid password"),
    Duplicated_USER_NAME(HttpStatus.CONFLICT, "Duplicated UserName"),

    ;


    private final HttpStatus status;
    private final String message;
}
