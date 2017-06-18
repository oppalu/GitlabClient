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
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.TExamFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.TExerciseFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.THomeworkFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.TInfoFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.TStudentFragment;
import com.example.phoebegl.gitlabclient.ui.view.BottomBar;
import com.example.phoebegl.gitlabclient.ui.view.BottomBarTab;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by phoebegl on 2017/6/18.
 */

public class TeacherMainFragment extends BaseFragment {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];

    BottomBar mBottomBar;

    public static TeacherMainFragment newInstance() {
        Bundle args = new Bundle();
        TeacherMainFragment fragment = new TeacherMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teacher_main, container, false);

        if (savedInstanceState == null) {

            mFragments[FIRST] = TStudentFragment.getInstance();
            mFragments[SECOND] = TExerciseFragment.getInstance();
            mFragments[THIRD] = THomeworkFragment.getInstance();
            mFragments[FOURTH] = TExamFragment.getInstance();
            mFragments[FIFTH] = TInfoFragment.getInstance();

            loadMultipleRootFragment(R.id.teacher_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIFTH]);
        } else {
            mFragments[FIRST] = findFragment(TStudentFragment.class);
            mFragments[SECOND] = findFragment(TExerciseFragment.class);
            mFragments[THIRD] = findFragment(THomeworkFragment.class);
            mFragments[FOURTH] = findFragment(TExamFragment.class);
            mFragments[FIFTH] = findFragment(TInfoFragment.class);
        }

        initView(view);
        return view;
    }

    private void initView(View view) {
        EventBus.getDefault().register(this);

        mBottomBar = (BottomBar)view.findViewById(R.id.teacher_bottom);

        mBottomBar.addItem(new BottomBarTab(_mActivity, R.mipmap.ic_student,"学生"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_exercise,"练习"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_homework,"作业"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_exam,"考试"))
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
