package com.dian.project.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.dian.project.R;
import com.dian.project.data.utils.SharePreUtil;
import com.dian.project.data.utils.StatusBarUtil;
import com.dian.project.ui.adapter.MainPagerAdapter;
import com.dian.project.ui.base.BaseActivity;
import com.dian.project.ui.fragment.Fragment_Bill;
import com.dian.project.ui.fragment.Fragment_Car;
import com.dian.project.ui.fragment.Fragment_Circle;
import com.dian.project.ui.fragment.Fragment_Home;
import com.dian.project.ui.fragment.Fragment_Mine;
import com.dian.project.ui.weight.CustomViewPager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private CustomViewPager mMainViewPager;
    private BottomNavigationView mBottomView;
    private List<Fragment> list=new ArrayList<>();
    private Fragment_Home home;
    private Fragment_Circle circle;
    private Fragment_Car car;
    private Fragment_Bill bill;
    private Fragment_Mine mine;
    public Context mContext;
    private FragmentManager manager;
    private String sessionId;
    private String userId;

    @Override
    public int getId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mContext=this;
        StatusBarUtil.setTransparent(this);
        mMainViewPager = (CustomViewPager) findViewById(R.id.main_ViewPager);
        mBottomView = (BottomNavigationView) findViewById(R.id.bottom_View);
        //Viewpager禁止滑动
        mMainViewPager.setScanScroll(false);

        sessionId = SharePreUtil.getStringData(this, "sessionId", "");
        userId = SharePreUtil.getStringData(this, "userId", "");
        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        mMainViewPager.setCurrentItem(0);
                        break;
                    case R.id.circle:
                        mMainViewPager.setCurrentItem(1);
                        break;
                    case R.id.car:
                        mMainViewPager.setCurrentItem(2);
                        break;
                    case R.id.bill:
                        mMainViewPager.setCurrentItem(3);
                        break;
                    case R.id.mine:
                        if (userId == null|| userId.equals("")|| sessionId ==null|| sessionId.equals("")) {
                            Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            return true;
                        }
                        mMainViewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        manager = getSupportFragmentManager();
        home = new Fragment_Home();
        circle = new Fragment_Circle();
        car = new Fragment_Car();
        bill = new Fragment_Bill();
        mine = new Fragment_Mine();
        list.add(home);
        list.add(circle);
        list.add(car);
        list.add(bill);
        list.add(mine);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(manager,mContext, list);
        mMainViewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
