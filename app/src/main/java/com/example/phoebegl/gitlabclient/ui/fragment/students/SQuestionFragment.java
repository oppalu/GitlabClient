package com.example.phoebegl.gitlabclient.ui.fragment.students;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.data.CourseService;
import com.example.phoebegl.gitlabclient.model.Question;
import com.example.phoebegl.gitlabclient.model.Readme;
import com.example.phoebegl.gitlabclient.ui.base.BaseBackFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teachers.QuestioninfoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by phoebegl on 2017/6/18.
 */

public class SQuestionFragment extends BaseBackFragment {

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
    @BindView(R.id.btn_readme)
    Button btn_readme;
    @BindView(R.id.readme)
    TextView readme;

    static Question info;
    private View mView;

    public static SQuestionFragment newInstance(Question info) {
        Bundle args = new Bundle();
        SQuestionFragment.info = info;
        SQuestionFragment fragment = new SQuestionFragment();
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
        mView = inflater.inflate(R.layout.fragment_student_question, container, false);
        ButterKnife.bind(this,mView);
        initView();
        return attachToSwipeBack(mView);
    }

    @OnClick(R.id.btn_readme)
    public void read(View view) {
        CourseService.getInstance().getReadme(info.getAssignmentId(),info.getId())
                .subscribe(new Subscriber<Readme>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Readme r) {
                        readme.setText(r.getContent());
                    }
                });
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
