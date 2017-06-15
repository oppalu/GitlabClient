package com.example.phoebegl.gitlabclient.ui.fragment.teacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.ui.base.BaseFragment;
import com.example.phoebegl.gitlabclient.ui.event.TabSelectedEvent;
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.exam.TExamFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.exercise.TExerciseFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.homework.THomeworkFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.info.TInfoFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.teacher.students.TStudentFragment;
import com.example.phoebegl.gitlabclient.ui.view.BottomBar;
import com.example.phoebegl.gitlabclient.ui.view.BottomBarTab;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by phoebegl on 2017/6/15.
 */

public class MainFragment extends BaseFragment {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];

    @BindView(R.id.teacher_bottom)
    BottomBar mBottomBar;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_main, container, false);

        if (savedInstanceState == null) {
            mFragments[FIRST] = TStudentFragment.getInstance();
            mFragments[SECOND] = TExamFragment.getInstance();
            mFragments[THIRD] = TExerciseFragment.getInstance();
            mFragments[FOURTH] = THomeworkFragment.getInstance();
            mFragments[FIFTH] = TInfoFragment.getInstance();

            loadMultipleRootFragment(R.id.teacher_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIFTH]);
        } else {
            mFragments[FIRST] = findFragment(TStudentFragment.class);
            mFragments[SECOND] = findFragment(TExamFragment.class);
            mFragments[THIRD] = findFragment(TExerciseFragment.class);
            mFragments[FOURTH] = findFragment(THomeworkFragment.class);
            mFragments[FIFTH] = findFragment(TInfoFragment.class);
        }

        initView(view);
        return view;
    }


    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return super.onCreateFragmentAnimator();
    }

    private void initView(View view) {
        EventBus.getDefault().register(this);
        mBottomBar.addItem(new BottomBarTab(_mActivity, R.mipmap.ic_student,"学生"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_exam,"考试"))
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


    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
