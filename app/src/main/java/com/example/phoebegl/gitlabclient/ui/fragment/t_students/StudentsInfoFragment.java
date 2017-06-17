package com.example.phoebegl.gitlabclient.ui.fragment.t_students;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.data.UserService;
import com.example.phoebegl.gitlabclient.model.Group;
import com.example.phoebegl.gitlabclient.model.Student;
import com.example.phoebegl.gitlabclient.ui.TeacherActivity;
import com.example.phoebegl.gitlabclient.ui.adapter.GroupAdapter;
import com.example.phoebegl.gitlabclient.ui.adapter.StudentAdapter;
import com.example.phoebegl.gitlabclient.ui.base.BaseBackFragment;
import com.example.phoebegl.gitlabclient.ui.event.TabSelectedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by phoebegl on 2017/6/16.
 */

public class StudentsInfoFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String ARG_STUDENT = "arg_student";
    @BindView(R.id.tstudents_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_layouts)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.students_list)
    RecyclerView list;

    static int groupid;
    private StudentAdapter adapter;
    private View mView;
    private boolean mInAtTop = true;
    private int mScrollTotal;

    public static StudentsInfoFragment newInstance(int groupid) {
        Bundle args = new Bundle();
        StudentsInfoFragment.groupid = groupid;
        StudentsInfoFragment fragment = new StudentsInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_teacher_students, container, false);
        ButterKnife.bind(this,mView);
        initView();
        return mView;
    }

    private void initView() {
        EventBus.getDefault().register(this);

        mToolbar.setTitle("学生列表");
        initToolbarNav(mToolbar);
        refreshLayout.setOnRefreshListener(this);

        adapter = new StudentAdapter(_mActivity);
        list.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        list.setLayoutManager(manager);
        list.setAdapter(adapter);

        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollTotal += dy;
                if (mScrollTotal <= 0) {
                    mInAtTop = true;
                } else {
                    mInAtTop = false;
                }
            }
        });
        initData();
    }

    public void initData() {
        UserService.getInstance().getStudentsByGroup(groupid)
                .subscribe(new Subscriber<List<Student>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Student> students) {
                        adapter.setDatas(students);
                    }
                });
    }

    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 2500);
    }

    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != TeacherActivity.FIRST) return;

        if (mInAtTop) {
            refreshLayout.setRefreshing(true);
            onRefresh();
        } else {
            scrollToTop();
        }
    }

    private void scrollToTop() {
        list.smoothScrollToPosition(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        list.setAdapter(null);
        EventBus.getDefault().unregister(this);
    }
}
