package com.employee.reports.er.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ERNotFoundException extends RuntimeException {

    public ERNotFoundException(String message) {
        super(message);
    }
}
