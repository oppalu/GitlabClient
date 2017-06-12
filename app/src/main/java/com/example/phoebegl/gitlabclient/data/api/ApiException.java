package com.example.phoebegl.gitlabclient.data.api;

/**
 * Created by phoebegl on 2017/6/11.
 */

public class ApiException extends Exception {
    private int code;
    private String message;

    public ApiException(Throwable throwable,String message) {
        super(throwable);
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
