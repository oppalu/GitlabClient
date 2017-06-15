package com.example.phoebegl.gitlabclient.ui.fragment.teacher.info;

import android.net.Uri;
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
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.homework.THomeworkFragment;

import butterknife.BindView;

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

    public static THomeworkFragment getInstance() {
        Bundle args = new Bundle();

        THomeworkFragment fragment = new THomeworkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_teacher_info, container, false);
        initView();
        return mView;
    }

    public void initView() {
        mToolbar.setTitle("个人信息");
//        avatar.setImageURI(Uri.parse(MyApp.getCurrentUser().getAvatar()));
        username.setText(MyApp.getCurrentUser().getUsername());
        name.setText(MyApp.getCurrentUser().getName());
        gender.setText(MyApp.getCurrentUser().getGender());
        email.setText(MyApp.getCurrentUser().getEmail());
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
}
