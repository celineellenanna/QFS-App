package com.example.pewpew.qfs.service;

public interface ApiHttpCallback<T> {
    void onCompletion(T response);
    void onError(String message);
}
