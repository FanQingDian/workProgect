package com.dian.project.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dian.project.R;
import com.dian.project.di.model.CircleListBean;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;

public class CircleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    public List<CircleListBean.ResultBean> list;

    public CircleAdapter(Context mContext, List<CircleListBean.ResultBean> list) {
        this.mContext = mContext;
        this.list = list;
    }
    AddGreat addGreat;

    public void setAddGreat(AddGreat addGreat) {
        this.addGreat = addGreat;
    }

    public interface AddGreat{
        void CallBack(int position,int circleId);
    }

    CancelGreat cancelGreat;

    public void setCancelGreat(CancelGreat cancelGreat) {
        this.cancelGreat = cancelGreat;
    }

    public interface CancelGreat{
        void CallBack(int position,int circleId);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_circle, viewGroup, false);
        ViewHolder holder=new ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder holder= (ViewHolder) viewHolder;
        Glide.with(mContext).load(list.get(i).getHeadPic())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mItemCircleHead);
        holder.mItemCircleTitle.setText(list.get(i).getContent());
        String image = list.get(i).getImage();
        holder.mItemCircleName.setText(list.get(i).getNickName());
        holder.mLikeNumber.setText(list.get(i).getGreatNum()+"");
        if (image == null||image.equals("")) return;
        String[] split = image.split(",");
        int length = split.length;
        GridLayoutManager manager = new GridLayoutManager(mContext, length);
        holder.mItemCircleRecy.setLayoutManager(manager);
        Item_CircleAdapter itemAdapter = new Item_CircleAdapter(mContext,split);
        holder.mItemCircleRecy.setAdapter(itemAdapter);
        final int id = list.get(i).getId();
        holder.mBtnLike.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                holder.mLikeNumber.setText(list.get(i).getGreatNum()+1+"");
                //addGreat.CallBack(i,id);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                //cancelGreat.CallBack(i,id);
                holder.mLikeNumber.setText(list.get(i).getGreatNum()-1+"");
            }
        });
        if (list.get(i).getWhetherGreat()==1) {
            holder.mBtnLike.setLiked(true);
        }else {
            holder.mBtnLike.setLiked(false);
        }
    }

    @Override
    public int getItemCount() {
        return list==null ? 0:list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mItemCircleHead;
        private TextView mItemCircleTitle;
        private RecyclerView mItemCircleRecy;
        private TextView mItemCircleName;
        private LikeButton mBtnLike;
        private TextView  mLikeNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemCircleName = (TextView) itemView.findViewById(R.id.item_circle_name);
            mItemCircleHead = (ImageView) itemView.findViewById(R.id.item_circle_head);
            mItemCircleTitle = (TextView) itemView.findViewById(R.id.item_circle_title);
            mItemCircleRecy = (RecyclerView) itemView.findViewById(R.id.item_circle_recy);
            mBtnLike =(LikeButton)itemView.findViewById(R.id.btn_like);
            mLikeNumber=(TextView)itemView.findViewById(R.id.like_number);
        }
    }
}
