package com.itheima.simpledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itheima.loopviewpager.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

public class SimpleDemo4 extends AppCompatActivity {

    private int[] imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_demo4);
        imageData = new int[]{R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4, R.mipmap.image5, R.mipmap.image6};
        ((LoopViewPager) findViewById(R.id.lvp_pager)).setImgData(imageData);
    }

}
