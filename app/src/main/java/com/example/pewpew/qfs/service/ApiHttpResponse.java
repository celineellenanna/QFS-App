package com.example.pewpew.qfs.service;

public class ApiHttpResponse {
    private Boolean status;
    private String message;

    public ApiHttpResponse(Boolean status, String message) {
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
