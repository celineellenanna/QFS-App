package com.example.pewpew.qfs.service;

import com.android.volley.Response;

public class ApiHttpGenericResponse<T> extends Response {
    private Boolean status;
    private String message;
    private T data;

    public ApiHttpGenericResponse(Boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }
}
