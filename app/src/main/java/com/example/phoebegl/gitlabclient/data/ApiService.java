package com.example.phoebegl.gitlabclient.data;

import com.example.phoebegl.gitlabclient.model.Account;
import com.example.phoebegl.gitlabclient.model.Group;
import com.example.phoebegl.gitlabclient.model.Student;
import com.example.phoebegl.gitlabclient.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by phoebegl on 2017/6/6.
 */

public interface ApiService {

    @POST("user/auth")
    Observable<UserInfo> login(@Body Account account);

    @GET("group")
    Observable<List<Group>> getGroup(@Header("Authorization") String token);

    @GET("group/{groupid}/students")
    Observable<List<Student>> getStudentsByGroup(@Header("Authorization") String token,@Path("groupid") int groupid);
}
