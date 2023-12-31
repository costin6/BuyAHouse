package com.fontys.s3.buyahouse.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserException extends ResponseStatusException {
    public InvalidUserException() {
        super(HttpStatus.BAD_REQUEST, "USER_INVALID");
    }

    public InvalidUserException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
