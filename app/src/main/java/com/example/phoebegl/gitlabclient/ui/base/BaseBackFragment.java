package com.example.phoebegl.gitlabclient.ui.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.phoebegl.gitlabclient.R;

/**
 * Created by phoebegl on 2017/6/14.
 */

public class BaseBackFragment extends BaseFragment {
    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
