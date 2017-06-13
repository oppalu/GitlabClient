package com.example.phoebegl.gitlabclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phoebegl.gitlabclient.data.ApiService;
import com.example.phoebegl.gitlabclient.data.RetrofitWrapper;
import com.example.phoebegl.gitlabclient.model.Account;
import com.example.phoebegl.gitlabclient.model.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by phoebegl on 2017/6/13.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.username)
    EditText et_username;
    @BindView(R.id.password)
    EditText et_password;
    @BindView(R.id.btn_login)
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        Account account = new Account(username,password);
        ApiService service = RetrofitWrapper.getInstance().create(ApiService.class);
        service.login(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        Toast.makeText(getApplication(), userInfo.getEmail(), Toast.LENGTH_SHORT)
                                .show();
                    }
                });
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
