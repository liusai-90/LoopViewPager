package com.itheima.loopviewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class DotOvalView extends View {

    public DotOvalView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
//        paint.setColor(Color.BLUE);
        canvas.drawOval(new RectF(0, 0, getWidth(), getHeight()), paint);
    }

}
