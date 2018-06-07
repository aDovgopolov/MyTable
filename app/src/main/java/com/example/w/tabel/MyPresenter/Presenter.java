package com.example.w.tabel.MyPresenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.w.tabel.Activity.GlobalActivity;
import com.example.w.tabel.MyPresenter.Beidg.SketchSheetView;
import com.example.w.tabel.R;
import com.example.w.tabel.Service.MyService;

public class Presenter {

    private BroadcastReceiver broadcastReceiver;
    public static final String TAG = "PRESENTER";
    private Context context;
    SketchSheetView sketchSheetView;
    TextView tv;
    int mathRandom = 0;

    public Presenter(TextView textView){
        this.tv = textView;
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String progress = "" + intent.getStringExtra(MyService.PERCENTAGE_STAMP);

                sketchSheetView.changeArc(Integer.parseInt(progress) * 12);

                if(progress.equals("30")){
                    context.stopService(new Intent(context , MyService.class));
                    context.startService(new Intent(context , MyService.class));

                    sketchSheetView.changeArc(0);
                    mathRandom = (int)(Math.random() * 1000000);
                    tv.setText(String.valueOf(mathRandom));
                }
            }
        };
    }

    public void setPb(SketchSheetView ssv) {
        context = ssv.getContext();
        this.sketchSheetView = ssv;
        registerReceiver();
        context.startService(new Intent(context , MyService.class));
    }

    public void registerReceiver(){
        LocalBroadcastManager.getInstance(context).registerReceiver((broadcastReceiver),
                new IntentFilter(MyService.DOWNLOAD_ACTION));
    }
}
