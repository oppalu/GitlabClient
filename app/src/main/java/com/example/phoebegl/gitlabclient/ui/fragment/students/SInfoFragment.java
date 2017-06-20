package com.example.phoebegl.gitlabclient.ui.fragment.students;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.MyApp;
import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.ui.base.BaseMainFragment;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by phoebegl on 2017/6/18.
 */

public class SInfoFragment extends BaseMainFragment {

    @BindView(R.id.sinfo_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.teacher_avatar)
    ImageView avatar;
    @BindView(R.id.student_name)
    TextView username;
    @BindView(R.id.teacher_name)
    TextView number;
    @BindView(R.id.teacher_gender)
    TextView gender;
    @BindView(R.id.teacher_email)
    TextView email;
    @BindView(R.id.git)
    TextView git;

    private View mView;

    public static SInfoFragment getInstance() {
        Bundle args = new Bundle();
        SInfoFragment fragment = new SInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_student_info, container, false);
        ButterKnife.bind(this,mView);
        initView();
        return mView;
    }

    public void initView() {
        mToolbar.setTitle("个人信息");
        if(MyApp.getCurrentUser().getAvatar() != null) {
            try {
                URL url = new URL(MyApp.getCurrentUser().getAvatar());
                avatar.setImageBitmap(BitmapFactory.decodeStream(url.openStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mToolbar.setTitle("个人信息");
        username.setText(MyApp.getCurrentUser().getName());
        number.setText("学号："+MyApp.getCurrentUser().getId());
        email.setText("邮箱："+MyApp.getCurrentUser().getEmail());
        git.setText("Git用户名："+MyApp.getCurrentUser().getGitUsername());
        if(MyApp.getCurrentUser().getGender().equals("male"))
            gender.setText("性别：男");
        if(MyApp.getCurrentUser().getGender().equals("female"))
            gender.setText("性别：女");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
}
