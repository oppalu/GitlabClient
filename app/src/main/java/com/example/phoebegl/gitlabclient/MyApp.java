package com.example.phoebegl.gitlabclient;

import android.app.Application;
import android.util.Base64;

import com.example.phoebegl.gitlabclient.model.UserInfo;

/**
 * Created by phoebegl on 2017/6/14.
 */

public class MyApp extends Application {
    private static UserInfo currentUser;
    private static String token;

    public static UserInfo getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserInfo currentUser) {
        MyApp.currentUser = currentUser;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String username,String password) {
        String token = Base64.encodeToString((username+":"+password).getBytes(),Base64.NO_WRAP);
        MyApp.token = token;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
