package com.example.phoebegl.gitlabclient.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.model.Group;
import com.example.phoebegl.gitlabclient.model.Student;
import com.example.phoebegl.gitlabclient.ui.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoebegl on 2017/6/16.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>  {
    private List<Student> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickListener mClickListener;

    public StudentAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<Student> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_student, parent, false);
        final StudentAdapter.MyViewHolder holder = new StudentAdapter.MyViewHolder(view);
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
    public void onBindViewHolder(StudentAdapter.MyViewHolder holder, int position) {
        Student item = mItems.get(position);
        holder.studentname.setText(String.valueOf(item.getName()));
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView studentname;

        public MyViewHolder(View itemView) {
            super(itemView);
            studentname= (TextView) itemView.findViewById(R.id.studentname);
        }
    }
}
