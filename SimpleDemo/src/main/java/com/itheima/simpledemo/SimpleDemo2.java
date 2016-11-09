package com.itheima.simpledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itheima.loopviewpager.LoopViewPager;

public class SimpleDemo2 extends AppCompatActivity {

    private String[] imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_demo2);
        imageData = new String[]{
                "http://d.hiphotos.baidu.com/image/h%3D200/sign=72b32dc4b719ebc4df787199b227cf79/58ee3d6d55fbb2fb48944ab34b4a20a44723dcd7.jpg",
                "http://pic.4j4j.cn/upload/pic/20130815/31e652fe2d.jpg",
                "http://pic.4j4j.cn/upload/pic/20130815/5e604404fe.jpg",
                "http://pic.4j4j.cn/upload/pic/20130909/681ebf9d64.jpg",
                "http://d.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d22dc28839a442309f79052d265.jpg",
        };
        ((LoopViewPager) findViewById(R.id.lvp_pager)).setImgData(imageData);
    }

}
