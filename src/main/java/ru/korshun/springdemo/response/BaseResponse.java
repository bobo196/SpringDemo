package ru.korshun.springdemo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import ru.korshun.springdemo.utils.Functions;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private int status;

    private String error;

    private T data;

    public BaseResponse(HttpStatus status, String error, T data) {
        this.status = Functions.getHttpCodeFromDesc(status);
        this.error = error;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = Functions.getHttpCodeFromDesc(status);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
