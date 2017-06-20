package com.example.phoebegl.gitlabclient.ui.fragment.students;

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
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.MyApp;
import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.model.Exam;
import com.example.phoebegl.gitlabclient.model.Question;
import com.example.phoebegl.gitlabclient.model.Status;
import com.example.phoebegl.gitlabclient.ui.adapter.QuestionAdapter;
import com.example.phoebegl.gitlabclient.ui.base.BaseBackFragment;
import com.example.phoebegl.gitlabclient.ui.event.StartBrotherEvent;
import com.example.phoebegl.gitlabclient.ui.fragment.teachers.QuestioninfoFragment;
import com.example.phoebegl.gitlabclient.ui.listener.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by phoebegl on 2017/6/18.
 */

public class SExaminfoFragment extends BaseBackFragment {

    @BindView(R.id.examinfo_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.question_list)
    RecyclerView list;
    @BindView(R.id.exam_name)
    TextView examname;
    @BindView(R.id.exam_description)
    TextView description;
    @BindView(R.id.exam_time)
    TextView examtime;
    @BindView(R.id.exam_status)
    TextView status;

    static Exam info;
    private QuestionAdapter adapter;
    private View mView;
    private boolean mInAtTop = true;
    private int mScrollTotal;

    public static SExaminfoFragment newInstance(Exam info) {
        Bundle args = new Bundle();
        SExaminfoFragment.info = info;
        SExaminfoFragment fragment = new SExaminfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_s_exam_info, container, false);
        ButterKnife.bind(this,mView);
        initView();
        return attachToSwipeBack(mView);
    }

    private void initView() {
        mToolbar.setTitle("详细信息");
        initToolbarNav(mToolbar);

        adapter = new QuestionAdapter(_mActivity);
        list.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        list.setLayoutManager(manager);
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                Log.i("assignmentid",String.valueOf(info.getId()));
                Log.i("questionid",String.valueOf(adapter.getItem(position).getId()));
                adapter.getItem(position).setAssignmentId(info.getId());
                EventBus.getDefault().post(new StartBrotherEvent(SQuestionFragment.newInstance(adapter.getItem(position))));
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
        examname.setText(info.getTitle());
        description.setText(info.getDescription());
        examtime.setText(info.getStartAt()+" ~ "+info.getEndAt());
        status.setText(Status.getInstance().getStatus(info.getStatus()));
        List<Question> questions = info.getQuestions();
        adapter.setDatas(questions);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        list.setAdapter(null);
    }
}
