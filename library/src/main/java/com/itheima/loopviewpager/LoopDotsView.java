package com.itheima.loopviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class LoopDotsView extends LinearLayout {

    private final int RECTANGLE = 1;
    private final int OVAL = 2;
    private int dotShape;//圆点形状
    private int dotWidth;//圆点宽度，默认0dp
    private int dotHeight;//圆点高度，默认0dp
    private int dotRange;//圆点距离，默认0dp
    private int dotColor;//圆点默认颜色
    private int dotSelectColor;//圆点选中颜色
    private int dotResource;//圆点默认资源
    private int dotSelectResource;//圆点选中资源

    public LoopDotsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoopDotsView);
        dotShape = typedArray.getInt(R.styleable.LoopDotsView_dotShape, RECTANGLE);
        dotWidth = (int) typedArray.getDimension(R.styleable.LoopDotsView_dotWidth, 0);
        dotHeight = (int) typedArray.getDimension(R.styleable.LoopDotsView_dotHeight, 0);
        dotRange = (int) typedArray.getDimension(R.styleable.LoopDotsView_dotRange, 0);
        dotColor = typedArray.getColor(R.styleable.LoopDotsView_dotColor, 0);
        dotSelectColor = typedArray.getColor(R.styleable.LoopDotsView_dotSelectColor, 0);
        dotResource = typedArray.getResourceId(R.styleable.LoopDotsView_dotResource, 0);
        dotSelectResource = typedArray.getResourceId(R.styleable.LoopDotsView_dotSelectResource, 0);
        typedArray.recycle();
    }

    /**
     * 初始化圆点
     */
    public void initDots(int length) {
        removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (dotWidth > 0) {
            params.width = dotWidth;
        }
        if (dotHeight > 0) {
            params.height = dotHeight;
        }
        for (int i = 0; i < length; i++) {
            View view = null;
            if (dotShape == RECTANGLE) {
                view = new View(getContext());
            } else if (dotShape == OVAL) {
                view = new DotOvalView(getContext());
            }
            if (i == 0) {
                params.setMargins(0, 0, 0, 0);
                if (dotSelectResource != 0) {
                    view.setBackgroundResource(dotSelectResource);
                } else {
                    if (dotShape == RECTANGLE){
                        view.setBackgroundColor(dotSelectColor);
                    }else{
                        ((DotOvalView)view).change(dotSelectColor);
                    }
                }
            } else {
                if (getOrientation() == VERTICAL) {
                    params.setMargins(0, dotRange, 0, 0);
                } else {
                    params.setMargins(dotRange, 0, 0, 0);
                }
                if (dotResource != 0) {
                    view.setBackgroundResource(dotResource);
                } else {
                    if (dotShape == RECTANGLE){
                        view.setBackgroundColor(dotColor);
                    }else{
                        ((DotOvalView)view).change(dotColor);
                    }

                }
            }
            view.setLayoutParams(params);
            addView(view);
        }
    }

    public void update(int index, int dotIndex) {
        if (index >= 0) {
            if (dotSelectResource != 0) {
                getChildAt(index).setBackgroundResource(dotSelectResource);
            } else {
                if (dotShape == RECTANGLE){
                    getChildAt(index).setBackgroundColor(dotSelectColor);
                }else{
                    ((DotOvalView)getChildAt(index)).change(dotSelectColor);
                }
            }
        }
        if (dotIndex >= 0) {
            if (dotResource != 0) {
                getChildAt(dotIndex).setBackgroundResource(dotResource);
            } else {
                if (dotShape == RECTANGLE){
                    getChildAt(dotIndex).setBackgroundColor(dotColor);
                }else{
                    ((DotOvalView)getChildAt(dotIndex)).change(dotColor);
                }
            }
        }
    }

}
