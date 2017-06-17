package com.example.phoebegl.gitlabclient.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.model.Group;
import com.example.phoebegl.gitlabclient.ui.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {
    private List<Group> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickListener mClickListener;

    public GroupAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<Group> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_group, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Group item = mItems.get(position);
        holder.groupid.setText(String.valueOf(item.getId()));
        holder.groupname.setText(item.getName());
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public int getGroupId(int position) {
        return mItems.get(position).getId();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView groupid;
        private TextView groupname;

        public MyViewHolder(View itemView) {
            super(itemView);
            groupid = (TextView) itemView.findViewById(R.id.groupid);
            groupname = (TextView) itemView.findViewById(R.id.groupname);

        }
    }
}
