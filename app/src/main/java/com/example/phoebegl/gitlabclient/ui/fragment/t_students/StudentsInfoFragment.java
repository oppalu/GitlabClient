package com.example.phoebegl.gitlabclient.ui.fragment.t_students;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.ui.base.BaseBackFragment;

import butterknife.BindView;

/**
 * Created by phoebegl on 2017/6/16.
 */

public class StudentsInfoFragment extends BaseBackFragment {

    private static final String ARG_STUDENT = "arg_student";
    @BindView(R.id.tstudents_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_layouts)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.students_list)
    RecyclerView list;



}
