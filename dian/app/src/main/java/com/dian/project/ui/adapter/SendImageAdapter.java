package com.dian.project.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dian.project.R;

import java.util.List;

public class SendImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    public List<Uri> list;

    public SendImageAdapter(Context mContext, List<Uri> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = View.inflate(mContext, R.layout.image_layout, null);
        ViewHolder holder=new ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder= (ViewHolder) viewHolder;
        holder.mLayoutImage.setImageURI(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mLayoutImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLayoutImage = (ImageView) itemView.findViewById(R.id.layout_image);
        }
    }
}
