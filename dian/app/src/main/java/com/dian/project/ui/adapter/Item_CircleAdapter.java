package com.dian.project.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.dian.project.R;

public class Item_CircleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    public String[] array;

    public Item_CircleAdapter(Context mContext, String[] array) {
        this.mContext = mContext;
        this.array = array;
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

        if (array.length == 1) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(750,750);
            holder.mLayoutImage.setLayoutParams(layoutParams);
        }else if(array.length == 2){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(375,375);
            holder.mLayoutImage.setLayoutParams(layoutParams);
        }else {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(250,250);
            holder.mLayoutImage.setLayoutParams(layoutParams);
        }
        Glide.with(mContext).load(array[i]).into(holder.mLayoutImage);
    }

    @Override
    public int getItemCount() {
        return array==null?0:array.length;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mLayoutImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLayoutImage = (ImageView) itemView.findViewById(R.id.layout_image);
        }
    }
}
