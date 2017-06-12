package com.example.phoebegl.gitlabclient.data;

import com.example.phoebegl.gitlabclient.model.Account;
import com.example.phoebegl.gitlabclient.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by phoebegl on 2017/6/6.
 */

public interface ApiService {

    @POST("user/auth")
    Call<UserInfo> login(@Body Account account);
}
