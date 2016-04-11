package ch.hsr.qfs.service.apiclient;

public interface ApiHttpCallback<T> {
    void onCompletion(T response);
    void onError(String message);
}
