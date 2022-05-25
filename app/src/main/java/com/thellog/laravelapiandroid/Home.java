package com.thellog.laravelapiandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.thellog.laravelapiandroid.Adapter.BannerAdapter;
import com.thellog.laravelapiandroid.Adapter.HomeAdapter;
import com.thellog.laravelapiandroid.Model.Home_Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator3;

public class Home extends AppCompatActivity {
    private RecyclerView recyclerViewHome;
    private HomeAdapter homeAdapter;
    private ViewPager2 mViewPager2;
    private CircleIndicator3 mCircleIndicator3;
    private List<ImgBanner> mimgBannerList;
    private HomeAdapter.RecyclerviewOnclickListener listener;
    private Timer mTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewHome = findViewById(R.id.recycViewhome);

        mViewPager2 = findViewById(R.id.view_paper2);
        mCircleIndicator3 = findViewById(R.id.circleImg3);



        setOnClickListener();
        homeAdapter = new HomeAdapter(this);




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , RecyclerView.VERTICAL,false);
        recyclerViewHome.setLayoutManager(linearLayoutManager);
        homeAdapter.setData(getListHome() , listener);
        recyclerViewHome.setAdapter(homeAdapter);

        mimgBannerList = getListImgBanner();
        BannerAdapter bannerAdapter = new BannerAdapter(mimgBannerList);
        mViewPager2.setAdapter(bannerAdapter);
        mCircleIndicator3.setViewPager(mViewPager2);


       autoSlideImgers();



    }

    private void setOnClickListener() {
        listener = new HomeAdapter.RecyclerviewOnclickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext() , Video.class);
                startActivity(intent);
            }
        };
    }

    private List<Home_Model> getListHome() {

     List<Home_Model> list = new ArrayList<>();
     list.add(new Home_Model(R.drawable.study1 , "Khoa Toan"));
        list.add(new Home_Model(R.drawable.study2 , "Khoa Toan"));
        list.add(new Home_Model(R.drawable.study3 , "Khoa Toan"));
        list.add(new Home_Model(R.drawable.study4 , "Khoa Toan"));

        return list;
    }

    private List<ImgBanner> getListImgBanner()
    {
        List<ImgBanner> list = new ArrayList<>();
        list.add( new ImgBanner(R.drawable.study1));
        list.add( new ImgBanner(R.drawable.study2));
        list.add( new ImgBanner(R.drawable.study3));
        list.add( new ImgBanner(R.drawable.study4));
        return list;

    }
    //Xu li cho hinh chay
    private void autoSlideImgers(){
        if(mimgBannerList == null || mimgBannerList.isEmpty() || mViewPager2 == null)
        {
            return;
        }

        //Init timer
        if (mTimer ==  null ){
            mTimer = new Timer();
        }

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currenItem = mViewPager2.getCurrentItem();
                        int totalItem = mimgBannerList.size() - 1 ;
                        if(currenItem < totalItem){
                            currenItem ++;
                            mViewPager2.setCurrentItem(currenItem);

                        }
                        else {
                            mViewPager2.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500 , 3000);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
       if(mTimer != null){
           mTimer.cancel();
           mTimer = null;
       }
    }

}
