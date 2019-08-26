package com.dian.project.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dian.project.R;
import com.dian.project.data.utils.ProgressDialog;
import com.dian.project.data.utils.SharePreUtil;
import com.dian.project.di.loader.SmallLoader;
import com.dian.project.di.model.HttpBean;
import com.dian.project.ui.adapter.SendImageAdapter;
import com.dian.project.ui.base.BaseActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.functions.Action1;

public class SendCircleActivity extends BaseActivity {

    private TextView mSnedTitle;
    private ImageView mBtnSend;
    private EditText mSendEdit;
    private RecyclerView mSendRecy;
    private ImageView mBtnJia;
    public int REQUEST_CODE_CHOOSE=200;
    private List<Uri> result=new ArrayList<>();
    private SmallLoader loader;
    private List<MultipartBody.Part> parts = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            result = Matisse.obtainResult(data);
            if (result.size()!=0&& result !=null) {
                mSendRecy.setVisibility(View.VISIBLE);
                mBtnJia.setVisibility(View.GONE);
                SendImageAdapter imageAdapter = new SendImageAdapter(this, result);
                mSendRecy.setAdapter(imageAdapter);
            }
        }
    }

    @Override
    public int getId() {
        return R.layout.activity_send_circle;
    }

    @Override
    protected void initView() {

        loader = new SmallLoader();
        mSnedTitle = (TextView) findViewById(R.id.sned_title);
        mBtnSend = (ImageView) findViewById(R.id.btn_send);
        mSendEdit = (EditText) findViewById(R.id.send_edit);
        mSendRecy = (RecyclerView) findViewById(R.id.send_recy);
        mBtnJia = (ImageView)findViewById(R.id.btn_jia);

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mSendRecy.setLayoutManager(manager);

        mBtnSend.setOnClickListener(this);
        mBtnJia.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                String content = mSendEdit.getText().toString().trim();
                String imageUrl="";
                for (int i = 0; i < result.size(); i++) {
                    Uri uri = result.get(i);
                    addHeaderPhoto(uri);
                }
                ProgressDialog.getInstance().show(this,"发布中。。。");

                loader.sendCircleData(19,content,parts).subscribe(new Action1<HttpBean>() {
                    @Override
                    public void call(HttpBean httpBean) {
                        ProgressDialog.getInstance().dismiss();
                        if (httpBean.getStatus().equals("0000")) {
                            Toast.makeText(SendCircleActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(SendCircleActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ProgressDialog.getInstance().dismiss();
                        Toast.makeText(SendCircleActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                        String message = throwable.getMessage();
                        Log.e("SendCircleActivity", "发布圈子message:" + message);
                    }
                });
                break;
            case R.id.btn_jia:
                Matisse.from(this)
                        .choose(MimeType.allOf())//图片类型
                        .countable(true)//true:选中后显示数字;false:选中后显示对号
                        .maxSelectable(9)//可选的最大数
                        .capture(true)//选择照片时，是否显示拍照
                        .captureStrategy(new CaptureStrategy(true, "com.dian.project"))
                        .imageEngine(new GlideEngine())//图片加载引擎
                        .forResult(REQUEST_CODE_CHOOSE);
                break;
        }
    }
    public void addHeaderPhoto(Uri uri){
        //将uri类型转换成file
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String string = cursor.getString(columnIndex);
        File file = new File(string);
        //将file类型转换成part
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        parts.add(part);
    }
}
