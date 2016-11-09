package com.itheima.loopviewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

public class DotOvalView extends ImageView {

    public DotOvalView(Context context) {
        super(context);
    }

    Bitmap bitmap;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap bitmap2 = getRoundBitmap();
//            final Rect rectSrc = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//            final Rect rectDest = new Rect(0, 0, getWidth(), getHeight());

            paint.reset();
            canvas.drawBitmap(bitmap2, 0, 0, paint);
        }
    }

    Paint paint;

    private Bitmap getRoundBitmap() {
        Toast.makeText(getContext(), "getRoundBitmap", Toast.LENGTH_SHORT).show();

        Bitmap output = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        paint = new Paint();

//        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(false);
        paint.setColor(Color.YELLOW);
//        int x = bitmap.getWidth();
//        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//        paint.setXfermode(Xfermode.)
//        canvas.drawOval(new RectF(5, 5, getWidth(), getHeight()), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawOval(new RectF(0, 0, getWidth(), getHeight()), paint);
//        paint.setColor(Color.GREEN);

//        canvas.drawARGB(0, 0, 0, 0);
        paint.setXfermode(null);
        canvas.drawBitmap(bitmap,0, 0, paint);
        return output;
    }

}
