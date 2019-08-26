package com.dian.project.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dian.project.R;
import com.dian.project.data.utils.SharePreUtil;
import com.dian.project.di.loader.SmallLoader;
import com.dian.project.di.model.BannerBean;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import rx.functions.Action1;

public class Fragment_Home extends Fragment implements View.OnClickListener {

    private View mView;
    private TextView mBtnHomeSreach;
    private XBanner mHomeBanner;
    private RecyclerView mRxxpRecy;
    private RecyclerView mMlshRecy;
    private RecyclerView mPzshRecy;
    private SmallLoader loader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_home, null);
        initView();
        initData();
        return mView;
    }

    private void initData() {
        loader = new SmallLoader();
        loader.getBannerData()
                .subscribe(new Action1<BannerBean>() {
                    @Override
                    public void call(BannerBean bannerBean) {
                        List<BannerBean.ResultBean> result = bannerBean.getResult();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        String message = throwable.getMessage();
                        Log.e("Fragment_Home", "bannerMessage:"+message);
                    }
                });
    }

    private void initView() {
        mBtnHomeSreach = (TextView) mView.findViewById(R.id.btn_home_sreach);
        mHomeBanner = (XBanner) mView.findViewById(R.id.home_banner);
        mRxxpRecy = (RecyclerView) mView.findViewById(R.id.rxxp_recy);
        mMlshRecy = (RecyclerView) mView.findViewById(R.id.mlsh_recy);
        mPzshRecy = (RecyclerView) mView.findViewById(R.id.pzsh_recy);

        mView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
