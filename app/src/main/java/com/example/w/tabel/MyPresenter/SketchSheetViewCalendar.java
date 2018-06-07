package com.example.w.tabel.MyPresenter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.example.w.tabel.Model.Person;
import com.example.w.tabel.R;

public class SketchSheetViewCalendar extends View {

    Paint p;
    int x;
    int y;
    int r;
    Context context;
    Canvas canvas;

    public SketchSheetViewCalendar(Context context) {
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
            r = Math.min(x, y);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        p.setStrokeWidth(2);
        canvas.drawCircle(x, y, r, p);
    }

    public void changeColor(int i){
        int color = ContextCompat.getColor(context,R.color.privat);
        if(i ==1){
            p.setColor(color);
            p.setStyle(Paint.Style.FILL);
        }else if (i == 2){
            color = ContextCompat.getColor(context,R.color.colorred);
            p.setColor(color);
            p.setStyle(Paint.Style.FILL);
        }else if(i == 3){
            color = ContextCompat.getColor(context,R.color.privat);
            p.setColor(color);
            p.setStyle(Paint.Style.STROKE);
        }else if( i ==4){
            color = ContextCompat.getColor(context,R.color.colordarkgray);
            p.setColor(color);
            p.setStyle(Paint.Style.FILL_AND_STROKE);
        }else{
            p.setColor(color);
            p.setStyle(Paint.Style.STROKE);
        }
        this.invalidate();
    }

}
