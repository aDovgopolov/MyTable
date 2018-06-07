package com.example.w.tabel.MyPresenter.Beidg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.w.tabel.R;

public class SketchSheetView extends View {

    Paint p;
    RectF oval;
    int x;
    int y;
    int r;
    int sweepAngle;
    Context context;

    public SketchSheetView(Context context, int x, int y, int r) {
        super(context);
        this.context = context;
        p = new Paint();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            x = (right - left) / 2;
            y = (bottom - top) / 2;
            r = Math.min(x/2, y/2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int color = ContextCompat.getColor(context, R.color.privat);
        p.setColor(color);
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(x, y, r, p);

        oval = new RectF();
        oval.set(x - r, y - r, x + r, y + r);
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
        canvas.drawArc(oval, 270, sweepAngle, true, p);
    }

    public void changeArc(int sweepAngle) {
        this.sweepAngle = sweepAngle;
        this.invalidate();
    }
}
