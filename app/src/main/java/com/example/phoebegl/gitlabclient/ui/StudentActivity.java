package com.example.phoebegl.gitlabclient.ui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.EnterActivity;
import com.example.phoebegl.gitlabclient.MyApp;
import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.model.UserInfo;
import com.example.phoebegl.gitlabclient.ui.fragment.StudentMainFragment;
import com.example.phoebegl.gitlabclient.ui.fragment.TeacherMainFragment;

import org.greenrobot.eventbus.EventBus;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;

/**
 * Created by phoebegl on 2017/6/13.
 */

public class StudentActivity extends SupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp app = (MyApp)getApplication();
        app.activities.add(this);

        setContentView(R.layout.teacher_activity_main);

        if (savedInstanceState == null) {
            loadRootFragment(R.id.t_container, StudentMainFragment.newInstance());
        }

        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentCreated(SupportFragment fragment, Bundle savedInstanceState) {
                super.onFragmentCreated(fragment, savedInstanceState);
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    public void t_logout(View view) {
        MyApp.setCurrentUser(null);
        MyApp.setToken(null,null);
        Intent intent = new Intent(StudentActivity.this,EnterActivity.class);
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
