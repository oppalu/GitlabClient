package com.example.phoebegl.gitlabclient.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.model.Question;
import com.example.phoebegl.gitlabclient.model.analyse.QuestionResult;
import com.example.phoebegl.gitlabclient.model.analyse.testcase;
import com.example.phoebegl.gitlabclient.ui.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoebegl on 2017/7/4.
 */

public class AnalyseAdapter extends RecyclerView.Adapter<AnalyseAdapter.MyViewHolder> {

    private List<QuestionResult> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public AnalyseAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<QuestionResult> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public AnalyseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_analyse, parent, false);
        final AnalyseAdapter.MyViewHolder holder = new AnalyseAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AnalyseAdapter.MyViewHolder holder, int position) {
        QuestionResult item = mItems.get(position);

        holder.question_name.setText(item.getQuestionTitle());
        String metric = "git url:   "+item.getMetricData().getGit_url()+"\n";
        metric += "total line count:    "+item.getMetricData().getTotal_line_count()+"\n";
        metric += "comment line count:  "+item.getMetricData().getComment_line_count()+"\n";
        metric += "field count: "+item.getMetricData().getField_count()+"\n";
        metric += "method count:    "+item.getMetricData().getMethod_count()+"\n";
        metric += "max coc: "+item.getMetricData().getMax_coc();
        holder.metric.setText(metric);

        String succeeded = item.getTestResult().isCompile_succeeded() ? "测试通过" : "测试不通过";
        holder.compile_succeeded.setText(succeeded);
        holder.score.setText("成绩："+String.valueOf(item.getScoreResult().getScore()));

        int pass = 0;
        int unpass = 0;
        for(testcase t : item.getTestResult().getTestcases()) {
            if(t.isPassed())
                pass++;
            else
                unpass++;
        }
        holder.testcase.setText("测试用例通过"+pass+",未通过"+unpass);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public QuestionResult getItem(int position) {
        return mItems.get(position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView question_name;
        private TextView metric;
        private TextView compile_succeeded;
        private TextView testcase;
        private TextView score;

        public MyViewHolder(View itemView) {
            super(itemView);
            question_name = (TextView) itemView.findViewById(R.id.question_name);
            metric = (TextView) itemView.findViewById(R.id.metric);
            compile_succeeded = (TextView) itemView.findViewById(R.id.compile_succeeded);
            testcase= (TextView) itemView.findViewById(R.id.testcase);
            score = (TextView) itemView.findViewById(R.id.score);
        }
    }
}
