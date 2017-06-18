package com.example.phoebegl.gitlabclient.data;

import com.example.phoebegl.gitlabclient.MyApp;
import com.example.phoebegl.gitlabclient.data.util.RetrofitWrapper;
import com.example.phoebegl.gitlabclient.model.Exam;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by phoebegl on 2017/6/18.
 */

public class CourseService {

    private static CourseService instance;
    private ApiService service;

    private CourseService() {
        service = RetrofitWrapper.getInstance().create(ApiService.class);
    }

    public static CourseService getInstance() {
        if(instance == null)
            instance = new CourseService();
        return instance;
    }

    public Observable<List<Exam>> getExams(int courseId) {
        String token = MyApp.getToken();
        return service.getExams(token,courseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Exam>> getHomeworks(int courseId) {
        String token = MyApp.getToken();
        return service.getHomework(token,courseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Exam>> getExercises(int courseId) {
        String token = MyApp.getToken();
        return service.getExercise(token,courseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
