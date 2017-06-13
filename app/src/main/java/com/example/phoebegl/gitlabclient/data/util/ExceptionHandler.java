package com.example.phoebegl.gitlabclient.data.util;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;

import retrofit2.HttpException;

/**
 * Created by phoebegl on 2017/6/13.
 */

public class ExceptionHandler {

    public static ApiException handleException(Throwable e){
        if (e instanceof SocketException){
            return new ApiException(e,1001, "网络连接错误");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            return new ApiException(e,1002, "解析错误");
        }else if(e instanceof ConnectException){
            return new ApiException(e,1003, "连接失败");
        } else if(e instanceof HttpException) {
            return new ApiException(e,1004,"HTTP错误");
        }
        else {
            return new ApiException(e,1005, "未知错误");
        }
    }
}

