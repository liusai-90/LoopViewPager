package com.itheima.simpledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itheima.loopviewpager.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

public class SimpleDemo3 extends AppCompatActivity {

    private String[] imageData;
    private List<String> titleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_demo3);
        imageData = new String[]{
                "http://d.hiphotos.baidu.com/image/h%3D200/sign=72b32dc4b719ebc4df787199b227cf79/58ee3d6d55fbb2fb48944ab34b4a20a44723dcd7.jpg",
                "http://pic.4j4j.cn/upload/pic/20130815/31e652fe2d.jpg",
                "http://pic.4j4j.cn/upload/pic/20130815/5e604404fe.jpg",
                "http://pic.4j4j.cn/upload/pic/20130909/681ebf9d64.jpg",
                "http://d.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d22dc28839a442309f79052d265.jpg",
        };
        titleData = new ArrayList<>();
        for (int i = 0; i < imageData.length; i++) {
            titleData.add("我的轮播的标题" + (i + 1));
        }
        ((LoopViewPager) findViewById(R.id.lvp_pager)).setImgAndTitleData(imageData, titleData);
    }

}
