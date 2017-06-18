package com.example.phoebegl.gitlabclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.ui.base.BaseFragment;
import com.example.phoebegl.gitlabclient.ui.event.StartBrotherEvent;
import com.example.phoebegl.gitlabclient.ui.event.TabSelectedEvent;
import com.example.phoebegl.gitlabclient.ui.fragment.students.ExamFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.students.ExerciseFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.students.HomeworkFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.students.SInfoFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teachers.TExamFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teachers.TExerciseFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teachers.THomeworkFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teachers.TInfoFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teachers.TStudentFragment;
import com.example.phoebegl.gitlabclient.ui.view.BottomBar;
import com.example.phoebegl.gitlabclient.ui.view.BottomBarTab;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by phoebegl on 2017/6/18.
 */

public class StudentMainFragment extends BaseFragment {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private SupportFragment[] mFragments = new SupportFragment[4];

    BottomBar mBottomBar;

    public static StudentMainFragment newInstance() {
        Bundle args = new Bundle();
        StudentMainFragment fragment = new StudentMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teacher_main, container, false);

        if (savedInstanceState == null) {
            mFragments[FIRST] = ExamFragment.getInstance();
            mFragments[SECOND] = ExerciseFragment.getInstance();
            mFragments[THIRD] = HomeworkFragment.getInstance();
            mFragments[FOURTH] = SInfoFragment.getInstance();

            loadMultipleRootFragment(R.id.teacher_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            mFragments[FIRST] = findFragment(ExamFragment.class);
            mFragments[SECOND] = findFragment(ExerciseFragment.class);
            mFragments[THIRD] = findFragment(HomeworkFragment.class);
            mFragments[FOURTH] = findFragment(SInfoFragment.class);
        }

        initView(view);
        return view;
    }

    private void initView(View view) {
        EventBus.getDefault().register(this);

        mBottomBar = (BottomBar)view.findViewById(R.id.teacher_bottom);

        mBottomBar.addItem(new BottomBarTab(_mActivity, R.mipmap.ic_exam,"考试"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_exercise,"练习"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_homework,"作业"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_account,"个人信息"));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                EventBus.getDefault().post(new TabSelectedEvent(position));
            }
        });
    }

    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
