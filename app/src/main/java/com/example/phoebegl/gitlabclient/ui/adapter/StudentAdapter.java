package com.example.phoebegl.gitlabclient.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.model.UserInfo;
import com.example.phoebegl.gitlabclient.ui.listener.OnItemClickListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoebegl on 2017/6/16.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>  {
    private List<UserInfo> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickListener mClickListener;

    public StudentAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<UserInfo> items) {
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
        UserInfo item = mItems.get(position);

        if(item.getAvatar() != null) {
            try {
                holder.avatar.setImageBitmap(BitmapFactory.decodeStream(new URL(item.getAvatar()).openStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        holder.studentname.setText(item.getName());
        holder.studentnumber.setText(item.getNumber());
        holder.studentemail.setText(item.getEmail());
        holder.studentgit.setText("git名："+item.getGitUsername());
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatar;
        private TextView studentname;
        private TextView studentnumber;
        private TextView studentemail;
        private TextView studentgit;

        public MyViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.img_avatar);
            studentname= (TextView) itemView.findViewById(R.id.stuname);
            studentnumber= (TextView) itemView.findViewById(R.id.stunumber);
            studentemail= (TextView) itemView.findViewById(R.id.stuemail);
            studentgit= (TextView) itemView.findViewById(R.id.stugit);
        }
    }
}
