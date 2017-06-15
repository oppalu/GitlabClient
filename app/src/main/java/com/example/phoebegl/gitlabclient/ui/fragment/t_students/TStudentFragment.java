package com.example.phoebegl.gitlabclient.ui.fragment.t_students;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.ui.base.BaseMainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by phoebegl on 2017/6/14.
 * 老师界面的学生列表
 */

public class TStudentFragment extends BaseMainFragment {

    @BindView(R.id.tstudent_toolbar)
    Toolbar mToolbar;
    private View mView;

    public static TStudentFragment getInstance() {
        Bundle args = new Bundle();

        TStudentFragment fragment = new TStudentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_teacher_list, container, false);
        ButterKnife.bind(this,mView);
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
        mToolbar.setTitle("学生列表");
    }

}
