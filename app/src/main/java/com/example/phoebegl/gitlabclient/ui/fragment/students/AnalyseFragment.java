package com.example.phoebegl.gitlabclient.ui.fragment.students;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.data.CourseService;
import com.example.phoebegl.gitlabclient.model.Question;
import com.example.phoebegl.gitlabclient.model.Status;
import com.example.phoebegl.gitlabclient.model.analyse.Analyse;
import com.example.phoebegl.gitlabclient.model.analyse.QuestionResult;
import com.example.phoebegl.gitlabclient.ui.adapter.AnalyseAdapter;
import com.example.phoebegl.gitlabclient.ui.base.BaseBackFragment;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by phoebegl on 2017/7/4.
 */

public class AnalyseFragment extends BaseBackFragment {

    @BindView(R.id.analyse_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.question_list)
    RecyclerView list;
    @BindView(R.id.assign_name)
    TextView name;

    static Analyse info;
    private AnalyseAdapter adapter;
    private View mView;
    private boolean mInAtTop = true;
    private int mScrollTotal;

    public static AnalyseFragment newInstance(Analyse info) {
        Bundle args = new Bundle();
        AnalyseFragment fragment = new AnalyseFragment();
        AnalyseFragment.info = info;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_student_analyse, container, false);
        ButterKnife.bind(this,mView);
        initView();
        return attachToSwipeBack(mView);
    }

    private void initView() {
        mToolbar.setTitle("考试分析");
        initToolbarNav(mToolbar);
        name.setText("作业"+info.getAssignmentId()+"分析");

        adapter = new AnalyseAdapter(_mActivity);
        list.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        list.setLayoutManager(manager);
        list.setAdapter(adapter);

        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollTotal += dy;
                if (mScrollTotal <= 0) {
                    mInAtTop = true;
                } else {
                    mInAtTop = false;
                }
            }
        });
        initData();
    }

    private void initData() {
        List<QuestionResult> questions = info.getQuestionResult();
        Log.i("test",String.valueOf(questions.size()));
        adapter.setDatas(questions);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        list.setAdapter(null);
    }
}
