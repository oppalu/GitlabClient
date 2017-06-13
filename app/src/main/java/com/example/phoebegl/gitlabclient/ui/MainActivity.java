package com.example.phoebegl.gitlabclient.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.CommonToken;
import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.data.ApiService;
import com.example.phoebegl.gitlabclient.data.RetrofitWrapper;
import com.example.phoebegl.gitlabclient.model.Account;
import com.example.phoebegl.gitlabclient.model.UserInfo;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.textView)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
        tv.setText(CommonToken.getToken());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
