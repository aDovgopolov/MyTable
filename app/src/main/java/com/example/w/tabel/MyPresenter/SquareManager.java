package com.example.w.tabel.MyPresenter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.w.tabel.R;

import java.util.Random;


public class SquareManager {

    private final static String TAG = "Manager";

    private int table = 12;
    private int row = 4;

    private int delay = 200;

    private Handler handler;
    private int count, maxViewsVisible;
    private Random rnd = new Random();

    private Activity activity;
    private ImageView[] imageViews;
    private View[] views;
    private SquareManager() {
        views = new View[table * row];
    }

    public SquareManager(int table, int row) {
        this.table = table;
        this.row = row;
        views = new View[table*row];
        imageViews = new ImageView[table*row];
        handler = new Handler();
        maxViewsVisible = table*row/5;
    }

    public void create(FrameLayout frameLayout) {
        int fullSize = getScreenWidth() / 7;
        float padding = (float) (fullSize * 0.1);
        int size = (int) ((fullSize - fullSize/4 ) - padding * 2);

        int index = 0;
        for (int y = 0; y < row; y++) {
            for (int i = 0; i < table; i++) {

                ImageView vv = new ImageView(frameLayout.getContext());
                vv.setImageDrawable(frameLayout.getContext().getResources().getDrawable( R.drawable.rectangle));
                vv.setLayoutParams(new FrameLayout.LayoutParams(size, size));
                vv.setX(i * fullSize + padding);
                vv.setY(y * fullSize + padding);
                vv.setAlpha(0f);
                imageViews[index++] = vv;
                frameLayout.addView(vv);
            }
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int index = rnd.nextInt(table*row);
                    final ImageView v = imageViews[index];

                        if (v.getAlpha() == 1.0f) {
                            AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
                            alphaAnimation.setDuration(1000);
                            alphaAnimation.setFillAfter(true);
                            v.startAnimation(alphaAnimation);
                            count--;
                        } else if (v.getAlpha() == 0 && count < maxViewsVisible) {
                            v.setAlpha(0.99f);
                            AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
                            alphaAnimation.setDuration(1000);
                            alphaAnimation.setFillAfter(true);
                            v.startAnimation(alphaAnimation);
                            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    v.setAlpha(1f);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            count++;
                    }
                    handler.postDelayed(runnable, delay);
                }
            });
        }
    };

    public void start(Activity activity) {
        this.activity = activity;
        handler.postDelayed(runnable, delay);
    }

    public void stop() {
        handler.removeCallbacks(runnable);
        activity = null;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}