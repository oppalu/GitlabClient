package com.example.phoebegl.gitlabclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.phoebegl.gitlabclient.EnterActivity;
import com.example.phoebegl.gitlabclient.MyApp;
import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.ui.event.StartBrotherEvent;
import com.example.phoebegl.gitlabclient.ui.event.TabSelectedEvent;
import com.example.phoebegl.gitlabclient.ui.fragment.t_exam.TExamFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.t_exercise.TExerciseFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.t_homework.THomeworkFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.t_info.TInfoFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.t_students.TStudentFragment;
import com.example.phoebegl.gitlabclient.ui.view.BottomBar;
import com.example.phoebegl.gitlabclient.ui.view.BottomBarTab;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;

/**
 * Created by phoebegl on 2017/6/13.
 */

public class TeacherActivity extends SupportActivity {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];

    @BindView(R.id.teacher_bottom)
    BottomBar mBottomBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp app = (MyApp)getApplication();
        app.activities.add(this);

        setContentView(R.layout.teacher_main);
        ButterKnife.bind(this);

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

        initView();

        // 可以监听该Activity下的所有Fragment的18个 生命周期方法
        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentSupportVisible(SupportFragment fragment) {
                Log.i("MainActivity", "onFragmentSupportVisible--->" + fragment.getClass().getSimpleName());
            }
        });
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return super.onCreateFragmentAnimator();
    }

    private void initView() {
        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.ic_student,"学生"))
                .addItem(new BottomBarTab(this, R.mipmap.ic_exercise,"练习"))
                .addItem(new BottomBarTab(this, R.mipmap.ic_homework,"作业"))
                .addItem(new BottomBarTab(this, R.mipmap.ic_exam,"考试"))
                .addItem(new BottomBarTab(this, R.mipmap.ic_account,"个人信息"));

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

    public void t_logout(View view) {
        MyApp.setCurrentUser(null);
        MyApp.setToken(null,null);
        Intent intent = new Intent(TeacherActivity.this,EnterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        MyApp app = (MyApp)getApplication();
        app.activities.remove(this);
    }
}
