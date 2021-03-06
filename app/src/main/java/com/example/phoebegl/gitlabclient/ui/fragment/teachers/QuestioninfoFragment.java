package com.example.phoebegl.gitlabclient.ui.fragment.teachers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.model.Exam;
import com.example.phoebegl.gitlabclient.model.Question;
import com.example.phoebegl.gitlabclient.model.Status;
import com.example.phoebegl.gitlabclient.ui.adapter.QuestionAdapter;
import com.example.phoebegl.gitlabclient.ui.base.BaseBackFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by phoebegl on 2017/6/18.
 */

public class QuestioninfoFragment extends BaseBackFragment {

    @BindView(R.id.question_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.question_name)
    TextView questionname;
    @BindView(R.id.question_description)
    TextView description;
    @BindView(R.id.difficulty)
    TextView difficulty;
    @BindView(R.id.giturl)
    TextView giturl;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.creator)
    TextView creator;
    @BindView(R.id.duration)
    TextView duration;
    @BindView(R.id.knowledgeVos)
    TextView knowledgeVos;

    static Question info;
    private View mView;

    public static QuestioninfoFragment newInstance(Question info) {
        Bundle args = new Bundle();
        QuestioninfoFragment.info = info;
        QuestioninfoFragment fragment = new QuestioninfoFragment();
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
        mView = inflater.inflate(R.layout.fragment_question_info, container, false);
        ButterKnife.bind(this,mView);
        initView();
        return attachToSwipeBack(mView);
    }

    private void initView() {
        mToolbar.setTitle("问题详情");
        initToolbarNav(mToolbar);
        questionname.setText(info.getTitle());
        description.setText(info.getDescription());
        difficulty.setText(info.getDifficulty());
        giturl.setText(info.getGitUrl());
        type.setText(info.getType());
        creator.setText(info.getCreator().getName());
        duration.setText(String.valueOf(info.getDuration()));
        knowledgeVos.setText(info.getKnowledgeVos());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
