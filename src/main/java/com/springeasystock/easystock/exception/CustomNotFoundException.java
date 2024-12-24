package com.springeasystock.easystock.exception;

import lombok.Getter;

@Getter
public class CustomNotFoundException extends RuntimeException {
    private final Long id;

    public CustomNotFoundException(Long id) {
        super("ID of the missing object: " + id);
        this.id = id;
    }

}

