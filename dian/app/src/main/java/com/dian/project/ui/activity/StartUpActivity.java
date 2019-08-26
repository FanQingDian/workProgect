package com.dian.project.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dian.project.R;
import com.dian.project.data.utils.StatusBarUtil;
import com.dian.project.ui.base.BaseActivity;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/*
 *属性动画(透明度,放大效果)
 * rxjava倒计时跳转
 */
public class StartUpActivity extends BaseActivity {
    private ImageView mImgStartUp;
    private TextView mTvTime;
    public Long time=3L;
    private Subscription subscribe;

    @Override
    public int getId() {
        return R.layout.activity_startup;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTransparent(this);
        mImgStartUp = (ImageView) findViewById(R.id.img_startUp);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mTvTime.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();
        subscribe = Observable
                .interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        time--;
                        if (time == 0) {
                           toMain();
                        }
                        mTvTime.setText("跳过:" + time + "S");
                    }
                });

    }

    private void toMain() {
        addSubscription(subscribe);
        Intent intent = new Intent();
        intent.setClass(StartUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        toMain();
    }
    @PermissionSuccess(requestCode = 100)
    public void doSomething(){
        Log.e("StartUpActivity", "Permission:获取权限成功");
    }
    @PermissionFail(requestCode = 100)
    public void doFailSomething(){
        Log.e("StartUpActivity", "Permission:获取权限失败");
    }
}
