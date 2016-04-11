package ch.hsr.qfs.service.apiclient;

public class ApiHttpResponse<T> {
    private Boolean success;
    private String message;
    private T data;

    public ApiHttpResponse(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }


    public T getData() {
        return this.data;
    }
}
