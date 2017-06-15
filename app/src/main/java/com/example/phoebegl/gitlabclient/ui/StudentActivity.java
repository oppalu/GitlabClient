package com.example.phoebegl.gitlabclient.ui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.EnterActivity;
import com.example.phoebegl.gitlabclient.MyApp;
import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.model.UserInfo;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by phoebegl on 2017/6/13.
 */

public class StudentActivity extends SupportActivity {
    @BindView(R.id.tinfo_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.teacher_avatar)
    ImageView avatar;
    @BindView(R.id.teacher_username)
    TextView username;
    @BindView(R.id.teacher_name)
    TextView name;
    @BindView(R.id.teacher_gender)
    TextView gender;
    @BindView(R.id.teacher_email)
    TextView email;
    @BindView(R.id.btn_logout)
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp app = (MyApp)getApplication();
        app.activities.add(this);

        setContentView(R.layout.fragment_teacher_info);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        if(MyApp.getCurrentUser().getAvatar() != null) {
            try {
                URL url = new URL(MyApp.getCurrentUser().getAvatar());
                avatar.setImageBitmap(BitmapFactory.decodeStream(url.openStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mToolbar.setTitle("个人信息");
        username.setText("用户名："+MyApp.getCurrentUser().getUsername());
        name.setText("姓名："+MyApp.getCurrentUser().getName());
        email.setText("邮箱："+MyApp.getCurrentUser().getEmail());
        if(MyApp.getCurrentUser().getGender().equals("male"))
            gender.setText("性别：男");
        if(MyApp.getCurrentUser().getGender().equals("female"))
            gender.setText("性别：女");
    }

    @OnClick(R.id.btn_logout)
    public void logout() {
        MyApp.setCurrentUser(null);
        MyApp.setToken(null,null);
        Intent intent = new Intent(StudentActivity.this,EnterActivity.class);
        startActivity(intent);
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
}
