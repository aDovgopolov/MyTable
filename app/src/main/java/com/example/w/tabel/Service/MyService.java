package com.example.w.tabel.Service;

import android.app.Service;
import android.content.Intent;
import java.util.concurrent.TimeUnit;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyService extends Service {

    private ExecutorService es;
    private LocalBroadcastManager lbm;
    private Intent intent;
    public static final String PERCENTAGE_STAMP = "percent";
    public static final String DOWNLOAD_ACTION = "info.devexchanges.download";
    public static final String TAG = "MyService";
    public int i;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lbm = LocalBroadcastManager.getInstance(this);
        es = Executors.newFixedThreadPool(1);
        MyRun myRun =  new MyRun();
        es.execute(myRun);
        Log.d(TAG, "OnCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        i = 0;
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    class MyRun implements Runnable{

        public void run() {
                String str = "";
                try {
                    for ( i = 1; i <= 30; i ++){
                        TimeUnit.MILLISECONDS.sleep(1000);
                        intent = new Intent(DOWNLOAD_ACTION);
                        str = "" + i;
                        intent.putExtra(PERCENTAGE_STAMP,str);
                        lbm.sendBroadcast(intent);
                    }

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
