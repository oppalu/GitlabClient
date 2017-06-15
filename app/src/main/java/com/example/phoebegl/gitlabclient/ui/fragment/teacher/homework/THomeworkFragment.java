package com.example.phoebegl.gitlabclient.ui.fragment.teacher.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.ui.base.BaseMainFragment;

import butterknife.BindView;

/**
 * Created by phoebegl on 2017/6/14.
 */

public class THomeworkFragment extends BaseMainFragment {

    @BindView(R.id.thomework_toolbar)
    Toolbar mToolbar;
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
        mView = inflater.inflate(R.layout.fragment_teacher_homework, container, false);
        return mView;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        mToolbar.setTitle("作业列表");
    }

    public void onBackToFirstFragment() {
        _mBackToFirstListener.onBackToFirstFragment();
    }
}
