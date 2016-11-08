package com.itheima.loopviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class LoopViewPager<A, B> extends FrameLayout implements View.OnTouchListener {

    private int intervalTime;//轮播时间，默认3秒


    private ViewPager viewPager;//轮播页面
    private List<LoopDotsView> loopDotsViews = new ArrayList<>();//轮播圆点
    private List<LoopTitleView> loopTitleViews = new ArrayList<>();//轮播文本

    private A imgData;//图片数据
    private B titleData;//标题数据
    private int length;//数据长度
    private int vpIndex;//页面索引
    private int dotIndex;//圆点索引
    private final int SCROLL = 1;//轮播消息

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SCROLL:
                    vpIndex++;
                    viewPager.setCurrentItem(vpIndex);
                    handler.sendEmptyMessageDelayed(SCROLL, intervalTime);
                    break;
            }
        }
    };

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoopViewPager);
        intervalTime = typedArray.getInt(R.styleable.LoopViewPager_intervalTime, 0);
        typedArray.recycle();
        View.inflate(getContext(), R.layout.weight_loopviewpager, this);
        viewPager = (ViewPager) findViewById(R.id.vp_pager);
    }

    public void setImgData(A imgData) {
        this.imgData = imgData;
        length = (imgData instanceof List ? ((List) imgData).size() : ((int[]) imgData).length);
        init();
    }

    public void setImgAndTitleData(A imgData, B titleData) {
        this.titleData = titleData;
        setImgData(imgData);
    }

    /**
     * 初始化数据
     */
    private void init() {
        loopTitleViews.clear();
        loopDotsViews.clear();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof LoopTitleView) {
                loopTitleViews.add((LoopTitleView) view);
            } else if (view instanceof LoopDotsView) {
                loopDotsViews.add((LoopDotsView) view);
            }
        }
        for (LoopDotsView loopDotsView : loopDotsViews) {
            loopDotsView.initDots(length);
        }

        vpIndex = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % length;
        dotIndex = -1;
        viewPager.setAdapter(new LoopPagerAdapter());
        viewPager.addOnPageChangeListener(new LoopPageChangeListener());
        viewPager.setOnTouchListener(this);
        viewPager.setCurrentItem(vpIndex);
        if (intervalTime > 0) {
            handler.sendEmptyMessageDelayed(SCROLL, intervalTime);
        }
    }


    private class LoopPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int index = position % length;
            ImageView view = new ImageView(getContext());
            Glide.with(getContext()).load(imgData instanceof List ? ((List<String>) imgData).get(index) : ((int[]) imgData)[index]).centerCrop().into(view);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    private class LoopPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int index = position % length;
            if (titleData != null && loopTitleViews.size() > 0) {
                for (LoopTitleView loopTitleView : loopTitleViews) {
                    loopTitleView.setText((titleData instanceof List ? ((List<String>) titleData).get(index) : ((String[]) titleData)[index]));
                }
            }
            if (loopDotsViews.size() > 0) {
                for (LoopDotsView loopDotsView : loopDotsViews) {
                    loopDotsView.update(index, dotIndex);
                }
            }
            vpIndex = position;
            dotIndex = index;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (intervalTime > 0) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    handler.removeCallbacksAndMessages(null);
                    break;
                case MotionEvent.ACTION_UP:
                    handler.sendEmptyMessageDelayed(SCROLL, intervalTime);
                    break;
            }
        }
        return false;
    }

}
