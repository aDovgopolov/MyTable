package com.example.w.tabel.MyPresenter.Beidg;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.w.tabel.R;

public class CustomView extends RelativeLayout{

    public TextView textView1;
    public TextView textView2;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.custom_view, this);

        textView1 = findViewById(R.id.txt_1);
        textView2 = findViewById(R.id.txt_2);
    }

    public void bindView(String str1, String str2){
        textView1.setText(str1);
        textView2.setText(str2);
    }

}
