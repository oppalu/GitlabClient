package com.example.phoebegl.gitlabclient.ui.base;

import android.widget.Toast;

/**
 * Created by phoebegl on 2017/6/14.
 */

public class BaseMainFragment extends BaseFragment {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出登录", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
