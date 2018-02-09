package com.example.javier.paint1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintView extends View {
    static int color = Color.RED;
    static Paint.Style style = Paint.Style.STROKE;
    static float strokeWidth = 5;
    private final Paint paint = new Paint();
    private final Path path = new Path();
    private Bitmap bitmap;
    private Canvas backgroundCanvas;
    private float x0, y0, xN, yN, radius;

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x0 = x;
                y0 = y;
                switch (MainActivity.mode) {
                    case PATH:
                        xN = x0;
                        yN = y0;
                        path.moveTo(x0, y0);
                        break;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                switch (MainActivity.mode) {
                    case PATH:
                        x0 = xN;
                        y0 = yN;
                        path.quadTo(x0, y0, (x + x0) / 2, (y + y0) / 2);
                        break;
                }
                xN = x;
                yN = y;
                switch (MainActivity.mode) {
                    case CIRCLE:
                        radius = (float) Math.sqrt(Math.pow(xN - x0, 2) + Math.pow(yN - y0, 2));
                        break;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                switch (MainActivity.mode) {
                    case PATH:
                        x0 = xN;
                        y0 = yN;
                        break;
                }
                xN = x;
                yN = y;
                switch (MainActivity.mode) {
                    case LINE:
                        backgroundCanvas.drawLine(x0, y0, xN, yN, paint);
                        break;
                    case RECT:
                        backgroundCanvas.drawRect(x0, y0, xN, yN, paint);
                        break;
                    case CIRCLE:
                        backgroundCanvas.drawCircle(x0, y0, radius, paint);
                        break;
                    case PATH:
                        backgroundCanvas.drawPath(path, paint);
                        path.reset();
                        break;
                }
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawBitmap(bitmap, 0, 0, null);
        switch (MainActivity.mode) {
            case LINE:
                canvas.drawLine(x0, y0, xN, yN, paint);
                break;
            case RECT:
                canvas.drawRect(x0, y0, xN, yN, paint);
                break;
            case CIRCLE:
                canvas.drawCircle(x0, y0, radius, paint);
                break;
            case PATH:
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawPath(path, paint);
                break;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        backgroundCanvas = new Canvas(bitmap);
    }
}
