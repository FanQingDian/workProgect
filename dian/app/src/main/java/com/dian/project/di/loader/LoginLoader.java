package com.dian.project.di.loader;

import com.dian.project.data.api.ApiService;
import com.dian.project.data.http.ObjectLoader;
import com.dian.project.data.http.RetrofitServiceManager;
import com.dian.project.di.model.LoginBean;
import rx.Observable;
import rx.functions.Func1;

public class LoginLoader extends ObjectLoader {
    private ApiService mApiService ;
    public LoginLoader(){
        mApiService = RetrofitServiceManager.getInstance().create(ApiService.class);
    }
    public Observable<LoginBean> getLoginData(String name, String password){
        return observe(mApiService.getLoginData(name,password)).map(new Func1<LoginBean, LoginBean>() {
            @Override
            public LoginBean call(LoginBean loginBean) {
                return loginBean;
            }
        });
    }
}
