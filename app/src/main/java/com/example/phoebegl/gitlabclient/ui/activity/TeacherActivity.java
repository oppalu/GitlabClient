package com.example.phoebegl.gitlabclient.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.CommonToken;
import com.example.phoebegl.gitlabclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by phoebegl on 2017/6/13.
 */

public class TeacherActivity extends SupportActivity {
    @BindView(R.id.textView)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
        tv.setText("teacher:"+CommonToken.getToken());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
