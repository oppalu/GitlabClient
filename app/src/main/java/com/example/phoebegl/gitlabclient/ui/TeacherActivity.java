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
import com.example.phoebegl.gitlabclient.ui.fragment.TeacherMainFragment;
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
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;

/**
 * Created by phoebegl on 2017/6/13.
 */

public class TeacherActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp app = (MyApp)getApplication();
        app.activities.add(this);

        setContentView(R.layout.teacher_activity_main);

        if (savedInstanceState == null) {
            loadRootFragment(R.id.t_container, TeacherMainFragment.newInstance());
        }

        // 可以监听该Activity下的所有Fragment的18个 生命周期方法
        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentSupportVisible(SupportFragment fragment) {
                Log.i("MainActivity", "onFragmentSupportVisible--->" + fragment.getClass().getSimpleName());
            }

            @Override
            public void onFragmentCreated(SupportFragment fragment, Bundle savedInstanceState) {
                super.onFragmentCreated(fragment, savedInstanceState);
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    public void t_logout(View view) {
        MyApp.setCurrentUser(null);
        MyApp.setToken(null,null);
        Intent intent = new Intent(TeacherActivity.this,EnterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        MyApp app = (MyApp)getApplication();
        app.activities.remove(this);
    }
}
