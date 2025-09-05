package com.otd.otd_msa_back_life.configuration.model;

public class CustomException extends RuntimeException {

    private final int status;

    public CustomException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}