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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.itheima.loopviewpager.transformer.BaseTransformer;

import java.util.ArrayList;
import java.util.List;

public class LoopViewPager<A, B> extends FrameLayout implements View.OnTouchListener {

    private final int Accordion = 1;
    private final int Background2Foreground = 2;
    private final int CubeIn = 3;
    private final int DepthPage = 4;
    private final int Fade = 5;
    private final int FlipHorizontal = 6;
    private final int FlipPage = 7;
    private final int Foreground2Background = 8;
    private final int RotateDown = 9;
    private final int RotateUp = 10;
    private final int Stack = 11;
    private final int Tablet = 12;
    private final int ZoomIn = 13;
    private final int ZoomOutSlide = 14;
    private final int ZoomOut = 15;

    private final int MIN_TIME = 500;
    private int intervalTime;//轮播时间，默认3秒
    private int animTime;//动画时间
    private int animStyle;//动画样式
    private boolean scrollEnable;//是否可以手动滚动
    private boolean touchEnable;//触摸是否停止
    private CustomViewPager viewPager;//轮播页面
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
        animTime = typedArray.getInt(R.styleable.LoopViewPager_animTime, 0);
        animStyle = typedArray.getInt(R.styleable.LoopViewPager_animStyle, 0);

        scrollEnable = typedArray.getBoolean(R.styleable.LoopViewPager_scrollEnable, true);
        touchEnable = typedArray.getBoolean(R.styleable.LoopViewPager_touchEnable, true);
        if (intervalTime < MIN_TIME && intervalTime > 0) {
            intervalTime = MIN_TIME;
        }
        typedArray.recycle();
        View.inflate(getContext(), R.layout.weight_loopviewpager, this);
        viewPager = (CustomViewPager) findViewById(R.id.cvp_pager);
        if (animStyle > 0) {
            BaseTransformer t = null;
            switch (animStyle) {
//                case Accordion:
//                    t = new AccordionTransformer();
//                    break;
//                case Background2Foreground:
//                    t = new BackgroundToForegroundTransformer();
//                    break;
//                case CubeIn:
//                    t = new CubeInTransformer();
//                    break;
//                case DepthPage:
//                    t = new DepthPageTransformer();
//                    break;
//                case Fade:
//                    t = new FadeTransformer();
//                    break;
//                case FlipHorizontal:
//                    t = new FlipHorizontalTransformer();
//                    break;
//                case FlipPage:
//                    t = new FlipPageViewTransformer();
//                    break;
//                case Foreground2Background:
//                    t = new ForegroundToBackgroundTransformer();
//                    break;
//                case RotateDown:
//                    t = new RotateDownTransformer();
//                    break;
//                case RotateUp:
//                    t = new RotateUpTransformer();
//                    break;
//                case Stack:
//                    t = new StackTransformer();
//                    break;
//                case Tablet:
//                    t = new TabletTransformer();
//                    break;
//                case ZoomIn:
//                    t = new ZoomInTransformer();
//                    break;
//                case ZoomOutSlide:
//                    t = new ZoomOutSlideTransformer();
//                    break;
//                case ZoomOut:
//                    t = new ZoomOutTransformer();
//                    break;
                default:
                    Toast.makeText(getContext(), "动画效果未实现", Toast.LENGTH_SHORT).show();
                    break;
            }
            if (t != null)
                viewPager.setPageTransformer(true, t);
        }
    }

    public void setPageTransformer(ViewPager.PageTransformer t){
        viewPager.setPageTransformer(true, t);
    }

    public void setImgData(A imgData) {
        this.imgData = imgData;
        if (imgData instanceof List) {
            length = ((List) imgData).size();
        } else {
            if (imgData instanceof String[]) {
                length = ((String[]) imgData).length;
            } else {
                length = ((int[]) imgData).length;
            }
        }
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
                LoopDotsView loopDotsView = (LoopDotsView) view;
                loopDotsView.initDots(length);
                loopDotsViews.add(loopDotsView);
            }
        }
        vpIndex = 1000 * (Integer.MAX_VALUE % length);
        dotIndex = -1;
        viewPager.setAdapter(new LoopPagerAdapter());
        viewPager.addOnPageChangeListener(new LoopPageChangeListener());
        viewPager.setOnTouchListener(this);
        viewPager.setCurrentItem(vpIndex);
        if (intervalTime >= MIN_TIME) {
            handler.sendEmptyMessageDelayed(SCROLL, intervalTime);
        }
    }


    /**
     * 创建适配器
     */
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
            if (imgData instanceof List) {
                Glide.with(getContext()).load(((List) imgData).get(index)).centerCrop().into(view);
            } else {
                if (imgData instanceof String[]) {
                    Glide.with(getContext()).load(((String[]) imgData)[index]).centerCrop().into(view);
                } else {
                    Glide.with(getContext()).load(((int[]) imgData)[index]).centerCrop().into(view);
                }
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    /**
     * 同步显示圆点与标题
     */
    private class LoopPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int index = position % length;
            if (loopTitleViews.size() > 0 && titleData != null) {
                for (LoopTitleView loopTitleView : loopTitleViews) {
                    if (titleData instanceof List) {
                        loopTitleView.setText(((List<String>) titleData).get(index));
                    } else {
                        loopTitleView.setText(((String[]) titleData)[index]);
                    }
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

    /**
     * 触摸停止播放
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (intervalTime >= MIN_TIME && touchEnable) {
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

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (scrollEnable) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return true;
        }
    }

}
