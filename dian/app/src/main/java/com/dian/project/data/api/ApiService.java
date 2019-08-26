package com.dian.project.data.api;

import com.dian.project.di.model.BannerBean;
import com.dian.project.di.model.CircleListBean;
import com.dian.project.di.model.HttpBean;
import com.dian.project.di.model.LoginBean;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
    /*登录*/
    @FormUrlEncoded
    @POST(ApiConfig.LOGIN_URL)
    Observable<LoginBean> getLoginData(@Field("phone") String userName,
                                       @Field("pwd") String passWord);

    /*圈子列表*/
    @GET(ApiConfig.CIRCLELIST_URL)
    Observable<CircleListBean> getCircleData(@Query("page") int page,
                                             @Query("count")  int count);

    /*发布圈子*/
    @Multipart
    @POST(ApiConfig.SENDCIRCLE_URL)
    Observable<HttpBean> sendCircleData(@Query("commodityId") int commodityId,
                                        @Query("content") String content,
                                       @Part() List<MultipartBody.Part> parts);

    /*圈子点赞*/
    @FormUrlEncoded
    @POST(ApiConfig.ADDGREAT_URL)
    Observable<HttpBean> addGreatData(@Field("circleid") int circleid);

    /*取消点赞*/
    @DELETE(ApiConfig.CANCELCIRCLEGREAT)
    Observable<HttpBean> cancelGreatData(@Field("circleid") int circleid);

    /*banner列表*/
    @GET(ApiConfig.BANNER_URL)
    Observable<BannerBean> getBannerData();
}
