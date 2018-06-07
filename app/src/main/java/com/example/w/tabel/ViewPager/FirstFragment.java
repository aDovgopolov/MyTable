package com.example.w.tabel.ViewPager;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.w.tabel.R;
import com.example.w.tabel.MyPresenter.SquareManager;
import com.squareup.picasso.Picasso;

public class FirstFragment extends Fragment{

    final String LOG_TAG = "myFragment1";
    FrameLayout frameLayout;
    ImageView v1;
    ImageView v2;

    public FirstFragment() {}

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        int  k = Resources.getSystem().getDisplayMetrics().heightPixels;

        frameLayout = view.findViewById(R.id.my_table);
        frameLayout.setBackgroundColor(Color.WHITE);
        frameLayout.getLayoutParams().height = (k / 10) * 4;

        v1 = view.findViewById(R.id.my_screen);
        v1.getLayoutParams().height = (k / 10) * 6;

        //v2 = view.findViewById(R.id.my_screen_2);
        //v2.getLayoutParams().height = k / 10;

        Picasso.with(container.getContext()).load(R.drawable.scr1).fit().into(v1);

        SquareManager squareManager = new SquareManager(7, 3);
        squareManager.create(frameLayout);
        squareManager.start((Activity) container.getContext());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
