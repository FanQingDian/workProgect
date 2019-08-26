package com.dian.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dian.project.R;
import com.dian.project.data.utils.ProgressDialog;
import com.dian.project.data.utils.SharePreUtil;
import com.dian.project.di.loader.LoginLoader;
import com.dian.project.di.model.LoginBean;
import com.dian.project.ui.base.BaseActivity;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class LoginActivity extends BaseActivity {
    private TextInputLayout mNameLayout;
    private EditText mNameInput;
    private TextInputLayout mNamePasswordLayout;
    private EditText mNamePasswordInput;
    private Button mBtnLogin;
    private CompositeSubscription sCompositeSubscription ;
    private static final String TAG ="BaseActivity" ;
    private LoginLoader loginLoader;
    public Context mContext;

    @Override
    public int getId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mContext=this;
        mNameLayout = (TextInputLayout) findViewById(R.id.name_layout);
        mNameInput = (EditText) findViewById(R.id.name_input);
        mNamePasswordLayout = (TextInputLayout) findViewById(R.id.name_password_layout);
        mNamePasswordInput = (EditText) findViewById(R.id.name_password_input);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        loginLoader = new LoginLoader();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String name = mNameInput.getText().toString().trim();
                String pwd = mNamePasswordInput.getText().toString().trim();
                ProgressDialog.getInstance().show(this,"登录中。。。");
                Subscription subscription = loginLoader.getLoginData(name,pwd).subscribe(new Action1<LoginBean>() {
                    @Override
                    public void call(LoginBean loginBean) {
                        Log.e("LoginActivity", "dianLog"+loginBean.getMessage());
                        ProgressDialog.getInstance().dismiss();
                        if (!loginBean.getStatus() .equals("0000")) {
                            Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                        SharePreUtil.saveStringData(mContext,"sessionId",loginBean.getResult().getSessionId());
                        SharePreUtil.saveStringData(mContext,"userId",loginBean.getResult().getUserId()+"");
                        startActivity(new Intent(mContext,MainActivity.class));
                        finish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("LoginActivity", "ERROR:"+throwable.getMessage());
                        throwable.printStackTrace();
                    }
                });
                addSubscription(subscription);
                break;
        }
    }

}
