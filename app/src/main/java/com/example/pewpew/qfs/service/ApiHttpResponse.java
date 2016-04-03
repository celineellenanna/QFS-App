package com.example.pewpew.qfs.service;

import com.example.pewpew.qfs.domain.User;

public class ApiHttpResponse {
    private Boolean status;
    private String message;
    private User user;

    public ApiHttpResponse(Boolean status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public User getUser() { return this.user; }
}
