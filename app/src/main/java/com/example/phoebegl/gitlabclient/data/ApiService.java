package com.example.phoebegl.gitlabclient.data;

import com.example.phoebegl.gitlabclient.model.Account;
import com.example.phoebegl.gitlabclient.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by phoebegl on 2017/6/6.
 */

public interface ApiService {

    @POST("user/auth")
    Observable<UserInfo> login(@Body Account account);
}
