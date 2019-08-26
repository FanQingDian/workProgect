package com.dian.project.data.api;

public class ApiConfig {
    /*根接口*/
    public final static String BASE_URL="http://mobile.bwstudent.com/";
    /*登录*/
    public final static String LOGIN_URL="small/user/v1/login";
    /*圈子列表*/
    public final static String CIRCLELIST_URL="small/circle/v1/findCircleList";
    /*发布圈子*/
    public final static String SENDCIRCLE_URL="small/circle/verify/v1/releaseCircle";
    /*圈子点赞*/
    public final static String ADDGREAT_URL="small/circle/verify/v1/addCircleGreat";
    /*取消点赞*/
    public final static String CANCELCIRCLEGREAT="small/circle/verify/v1/cancelCircleGreat";
    /*banner列表*/
    public final static String BANNER_URL="small/commodity/v1/bannerShow";
}
