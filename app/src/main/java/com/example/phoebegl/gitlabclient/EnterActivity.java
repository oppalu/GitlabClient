package com.example.phoebegl.gitlabclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phoebegl.gitlabclient.data.ApiService;
import com.example.phoebegl.gitlabclient.data.RetrofitWrapper;
import com.example.phoebegl.gitlabclient.data.api.ApiException;
import com.example.phoebegl.gitlabclient.data.api.ExceptionHandler;
import com.example.phoebegl.gitlabclient.model.Account;
import com.example.phoebegl.gitlabclient.model.UserInfo;
import com.example.phoebegl.gitlabclient.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by phoebegl on 2017/6/13.
 */

public class EnterActivity extends AppCompatActivity {
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
        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();
        Account account = new Account(username,password);
        ApiService service = RetrofitWrapper.getInstance().create(ApiService.class);
        service.login(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                        Intent intent = new Intent(EnterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ApiException exception = ExceptionHandler.handleException(e);
                        Toast.makeText(getApplication(), exception.getMessage(), Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        if(userInfo == null)
                            Toast.makeText(getApplication(), "用户不存在", Toast.LENGTH_SHORT)
                                    .show();
                        CommonToken.setCurrentUser(userInfo);
                        CommonToken.setToken(username,password);
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
