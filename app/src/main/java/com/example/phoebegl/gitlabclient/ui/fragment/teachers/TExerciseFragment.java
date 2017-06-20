package com.example.phoebegl.gitlabclient.ui.fragment.teachers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoebegl.gitlabclient.MyApp;
import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.data.CourseService;
import com.example.phoebegl.gitlabclient.model.Exam;
import com.example.phoebegl.gitlabclient.ui.adapter.ExamAdapter;
import com.example.phoebegl.gitlabclient.ui.base.BaseMainFragment;
import com.example.phoebegl.gitlabclient.ui.event.StartBrotherEvent;
import com.example.phoebegl.gitlabclient.ui.event.TabSelectedEvent;
import com.example.phoebegl.gitlabclient.ui.fragment.TeacherMainFragment;
import com.example.phoebegl.gitlabclient.ui.listener.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by phoebegl on 2017/6/14.
 */

public class TExerciseFragment extends BaseMainFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.texam_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.exam_list)
    RecyclerView list;

    private View mView;
    private boolean mInAtTop = true;
    private int mScrollTotal;
    private ExamAdapter adapter;

    public static TExerciseFragment getInstance() {
        Bundle args = new Bundle();
        TExerciseFragment fragment = new TExerciseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_teacher_exam, container, false);
        ButterKnife.bind(this,mView);
        initView();
        return mView;
    }

    private void initView() {
        EventBus.getDefault().register(this);
        mToolbar.setTitle("练习列表");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        refreshLayout.setOnRefreshListener(this);

        adapter = new ExamAdapter(_mActivity);
        list.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        list.setLayoutManager(manager);
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                Log.i("examid",String.valueOf(adapter.getExam(position).getId()));
                EventBus.getDefault().post(new StartBrotherEvent(ExaminfoFragment.newInstance(adapter.getExam(position))));
            }
        });

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
        CourseService.getInstance().getExercises(MyApp.getCourseId())
                .subscribe(new Subscriber<List<Exam>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Exam> exams) {
                        adapter.setDatas(exams);
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
        if (event.position != TeacherMainFragment.FIRST) return;

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
