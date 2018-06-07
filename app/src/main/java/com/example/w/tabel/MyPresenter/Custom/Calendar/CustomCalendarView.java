package com.example.w.tabel.MyPresenter.Custom.Calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.example.w.tabel.MyPresenter.SketchSheetViewCalendar;
import com.example.w.tabel.R;

public class CustomCalendarView extends RelativeLayout{

    View view;
    RelativeLayout relativeLayout;
    public SketchSheetViewCalendar sketchSheetViewCalendar;
    Context context;

    public CustomCalendarView(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    public CustomCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        inflate(context, R.layout.custom_view_calendar, this);
        relativeLayout = findViewById(R.id.edit_relative);
        sketchSheetViewCalendar = new SketchSheetViewCalendar(context);
        view = sketchSheetViewCalendar;
        relativeLayout.addView(view);
    }
}
