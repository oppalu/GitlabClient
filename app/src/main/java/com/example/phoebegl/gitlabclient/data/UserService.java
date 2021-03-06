package com.example.phoebegl.gitlabclient.data;

import com.example.phoebegl.gitlabclient.MyApp;
import com.example.phoebegl.gitlabclient.data.util.RetrofitWrapper;
import com.example.phoebegl.gitlabclient.model.Account;
import com.example.phoebegl.gitlabclient.model.Group;
import com.example.phoebegl.gitlabclient.model.UserInfo;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by phoebegl on 2017/6/13.
 */

public class UserService {

    private static UserService instance;
    private ApiService service;

    private UserService() {
        service = RetrofitWrapper.getInstance().create(ApiService.class);
    }

    public static UserService getInstance() {
        if(instance == null)
            instance = new UserService();
        return instance;
    }

    public Observable<UserInfo> getUserInfo(String username,String password) {
        Account account = new Account(username,password);
        return service.login(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Group>> getGroup() {
        String token = MyApp.getToken();
        return service.getGroup(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<UserInfo>> getStudentsByGroup(int groupid) {
        String token = MyApp.getToken();
        return service.getStudentsByGroup(token,groupid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}