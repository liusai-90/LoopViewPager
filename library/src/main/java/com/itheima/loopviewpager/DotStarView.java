package com.itheima.loopviewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class DotStarView extends ImageView {

    public DotStarView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();

        if (drawable != null){
            Bitmap bitmap = ((BitmapDrawable)drawable ).getBitmap();
            bitmap = getRoundBitmap(bitmap);
            final Rect rectSrc = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final Rect rectDest = new Rect(0, 0, getWidth(), getHeight());
            canvas.drawBitmap(bitmap, rectSrc, rectDest, null);
        }

    }

    private Bitmap getRoundBitmap(Bitmap bitmap) {
        Paint paint = new Paint();
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//
//        Bitmap ovalBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//        Canvas ovalCanvas = new Canvas(ovalBitmap);
//        ovalCanvas.drawOval(new RectF(0, 0, getWidth(), getHeight()), paint);

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

//        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
//        int x = bitmap.getWidth();
//        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return output;


    }

}
