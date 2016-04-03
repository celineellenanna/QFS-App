package com.example.pewpew.qfs.service;

public interface ApiCallback<T> {
    void onCompletion(T response);
    void onError(String message);
}
