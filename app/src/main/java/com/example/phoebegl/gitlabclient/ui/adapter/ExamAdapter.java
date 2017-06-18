package com.example.phoebegl.gitlabclient.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.model.Exam;
import com.example.phoebegl.gitlabclient.model.Group;
import com.example.phoebegl.gitlabclient.model.Status;
import com.example.phoebegl.gitlabclient.ui.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoebegl on 2017/6/18.
 */

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.MyViewHolder>{

    private List<Exam> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickListener mClickListener;

    public ExamAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<Exam> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ExamAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_exam, parent, false);
        final ExamAdapter.MyViewHolder holder = new ExamAdapter.MyViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ExamAdapter.MyViewHolder holder, int position) {
        Exam item = mItems.get(position);
        holder.name.setText(item.getTitle());
        holder.time.setText(item.getStartAt()+" ~ "+item.getEndAt());
        holder.status.setText(Status.getInstance().getStatus(item.getStatus()));
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Exam getExam(int position) {
        return mItems.get(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView time;
        private TextView status;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.exam_name);
            time = (TextView) itemView.findViewById(R.id.examtime);
            status = (TextView) itemView.findViewById(R.id.examstatus);
        }
    }
}
