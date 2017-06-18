package com.example.phoebegl.gitlabclient.ui.fragment.teacher;

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
 * Created by phoebegl on 2017/6/14.
 * 老师个人信息
 */

public class TInfoFragment extends BaseMainFragment {

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

    private View mView;

    public static TInfoFragment getInstance() {
        Bundle args = new Bundle();

        TInfoFragment fragment = new TInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_teacher_info, container, false);
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
        username.setText("用户名："+MyApp.getCurrentUser().getUsername());
        name.setText("姓名："+MyApp.getCurrentUser().getName());
        email.setText("邮箱："+MyApp.getCurrentUser().getEmail());
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
