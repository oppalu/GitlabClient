package com.example.phoebegl.gitlabclient.data.api;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

public class ExceptionHandler {
    //对应HTTP的状态码
    private static final int BAD_REQUEST = 400;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int METHOD_NOT_ALLOWED = 405;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int CONFLICT = 409;
    private static final int PRECONDITION_FAILED = 412;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e){
        ApiException ex;
        if (e instanceof HttpException){             //HTTP错误
            HttpException httpException = (HttpException) e;
            switch(httpException.code()){
                case BAD_REQUEST:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case METHOD_NOT_ALLOWED:
                case REQUEST_TIMEOUT:
                case CONFLICT:
                case PRECONDITION_FAILED:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex = new ApiException(e, "网络错误");  //均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ex = new ApiException(e, "解析错误");//均视为解析错误
            return ex;
        }else if(e instanceof ConnectException){
            ex = new ApiException(e, "连接失败");
            return ex;
        }else {
            ex = new ApiException(e, "未知错误");//未知错误
            return ex;
        }
    }
}