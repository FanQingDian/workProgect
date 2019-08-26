package com.dian.project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dian.project.R;
import com.dian.project.data.utils.ProgressDialog;
import com.dian.project.data.utils.SharePreUtil;
import com.dian.project.di.loader.SmallLoader;
import com.dian.project.di.model.CircleListBean;
import com.dian.project.di.model.HttpBean;
import com.dian.project.ui.activity.SendCircleActivity;
import com.dian.project.ui.adapter.CircleAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

public class Fragment_Circle extends Fragment implements View.OnClickListener {

    private View mView;
    private ImageView mBtnRelease;
    private SmartRefreshLayout mCircleSmart;
    private RecyclerView mCircleRecy;
    private int page=1;
    private int count=10;
    private SmallLoader smallLoader;
    private List<CircleListBean.ResultBean> mList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_circle, null);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        mBtnRelease = (ImageView) mView.findViewById(R.id.btn_release);
        mCircleSmart = (SmartRefreshLayout) mView.findViewById(R.id.circle_smart);
        mCircleRecy = (RecyclerView) mView.findViewById(R.id.circle_recy);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mCircleRecy.setLayoutManager(manager);
        mBtnRelease.setOnClickListener(this);
        smallLoader = new SmallLoader();
    }

    private void initData() {
        ProgressDialog.getInstance().show(getActivity(),"请稍等。。。");
        Subscription subscribe = smallLoader.getCircleData(page, count)
                .subscribe(new Action1<CircleListBean>() {
                    @Override
                    public void call(CircleListBean circleListBean) {
                        String status = circleListBean.getStatus();
                        ProgressDialog.getInstance().dismiss();
                        if (!status.equals("0000")) {
                            return;
                        }
                        List<CircleListBean.ResultBean> result = circleListBean.getResult();
                        mList.addAll(result);
                        initRecy();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ProgressDialog.getInstance().dismiss();
                        String message = throwable.getMessage();
                        Log.e("Fragment_Circle", "圈子列表message:" + message);
                    }
                });

    }

    private void initRecy() {
        CircleAdapter circleAdapter = new CircleAdapter(getActivity(), mList);
        mCircleRecy.setAdapter(circleAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_release:
                Intent intent = new Intent(getActivity(), SendCircleActivity.class);
                startActivity(intent);
                break;
        }
    }


}
