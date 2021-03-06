package com.example.phoebegl.gitlabclient;

import android.app.Activity;
import android.app.Application;
import android.util.Base64;

import com.example.phoebegl.gitlabclient.model.UserInfo;

import java.util.ArrayList;

/**
 * Created by phoebegl on 2017/6/14.
 */

public class MyApp extends Application {
    private static UserInfo currentUser;
    private static String token;
    //只有一门课？？？
    private static int courseId = 2;
    public ArrayList<Activity> activities;

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
        if(username==null && password==null)
            MyApp.token = null;
        else {
            String token = "Basic " + Base64.encodeToString((username+":"+password).getBytes(),Base64.NO_WRAP);
            MyApp.token = token;
        }
    }

    public static int getCourseId() {
        return courseId;
    }

    public static void setCourseId(int courseId) {
        MyApp.courseId = courseId;
    }

    public void close() {
        for(Activity a : activities)
            a.finish();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        activities = new ArrayList<>();
    }
}
