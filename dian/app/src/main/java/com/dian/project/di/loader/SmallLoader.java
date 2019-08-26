package com.dian.project.di.loader;

import com.dian.project.data.api.ApiService;
import com.dian.project.data.http.ObjectLoader;
import com.dian.project.data.http.RetrofitServiceManager;
import com.dian.project.di.model.BannerBean;
import com.dian.project.di.model.CircleListBean;
import com.dian.project.di.model.HttpBean;
import com.dian.project.di.model.LoginBean;

import java.util.List;

import okhttp3.MultipartBody;
import rx.Observable;
import rx.functions.Func1;

public class SmallLoader  extends ObjectLoader {
    private ApiService mApiService ;
    public SmallLoader(){
        mApiService = RetrofitServiceManager.getInstance().create(ApiService.class);
    }
    /*圈子列表*/
    public Observable<CircleListBean> getCircleData(int page, int count) {
        return observe(mApiService.getCircleData(page,count)).map(new Func1<CircleListBean, CircleListBean>() {
            @Override
            public CircleListBean call(CircleListBean circleBean) {
                return circleBean;
            }
        });
    }

    /*发布圈子*/
    public Observable<HttpBean> sendCircleData(int id, String content, List<MultipartBody.Part> parts){
        return observe(mApiService.sendCircleData(id,content,parts)).map(new Func1<HttpBean, HttpBean>() {
            @Override
            public HttpBean call(HttpBean httpBean) {
                return httpBean;
            }
        });
    }

    /*bannerl列表*/
    public Observable<BannerBean> getBannerData(){
        return observe(mApiService.getBannerData())
                .map(new Func1<BannerBean, BannerBean>() {
            @Override
            public BannerBean call(BannerBean bannerBean) {
                return bannerBean;
            }
        });
    }

    /*首页列表*/



}
