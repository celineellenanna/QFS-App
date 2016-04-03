package com.example.pewpew.qfs.domain;

public class DefaultResponse {
    private Boolean status;
    private String message;

    public DefaultResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
