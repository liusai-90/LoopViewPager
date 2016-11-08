package com.itheima.simpledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itheima.loopviewpager.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

public class SimpleDemo3 extends AppCompatActivity {

    private int[] imageData;
    private List<String> titleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_demo3);
        imageData = new int[]{R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4, R.mipmap.image5, R.mipmap.image6};
        titleData = new ArrayList<>();
        for (int i = 0; i < imageData.length; i++) {
            titleData.add("我的轮播的标题" + (i + 1));
        }
        ((LoopViewPager) findViewById(R.id.lvp_pager)).setImgAndTitleData(imageData, titleData);
    }

}
