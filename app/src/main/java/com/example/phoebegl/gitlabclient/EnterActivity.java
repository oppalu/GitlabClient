package com.example.phoebegl.gitlabclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phoebegl.gitlabclient.data.UserService;
import com.example.phoebegl.gitlabclient.data.util.ApiException;
import com.example.phoebegl.gitlabclient.data.util.ExceptionHandler;
import com.example.phoebegl.gitlabclient.model.UserInfo;
import com.example.phoebegl.gitlabclient.ui.StudentActivity;
import com.example.phoebegl.gitlabclient.ui.TeacherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

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
        MyApp app = (MyApp)getApplication();
        app.activities.add(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();

        if(username.equals("") || password.equals(""))
            Toast.makeText(getApplication(),"用户名或密码不得为空！", Toast.LENGTH_SHORT).show();
        else {
            UserService.getInstance().getUserInfo(username,password)
                    .subscribe(new Subscriber<UserInfo>() {
                        @Override
                        public void onCompleted() {
                            Intent intent = null;
                            if(MyApp.getCurrentUser().getType().equals("student"))
                               intent = new Intent(EnterActivity.this, StudentActivity.class);
                            else if(MyApp.getCurrentUser().getType().equals("teacher"))
                                intent = new Intent(EnterActivity.this, TeacherActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(Throwable e) {
                            ApiException exception = ExceptionHandler.handleException(e);
                            if(exception.getCode()==1005)
                                Toast.makeText(getApplication(),"用户名或密码错误！", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplication(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(UserInfo userInfo) {
                            MyApp.setCurrentUser(userInfo);
                            MyApp.setToken(username,password);
                        }
                    });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp app = (MyApp)getApplication();
        app.activities.remove(this);
    }

    @Override
    public void onBackPressed() {
        MyApp app = (MyApp)getApplication();
        app.close();
    }
}
