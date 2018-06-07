package com.example.w.tabel.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.w.tabel.Fragment.CalendarFragment;
import com.example.w.tabel.Model.Person;
import com.example.w.tabel.MyPresenter.Beidg.CustomView;
import com.example.w.tabel.MyPresenter.MyRecycleViewAdapter;
import com.example.w.tabel.MyPresenter.Presenter;
import com.example.w.tabel.MyPresenter.Beidg.SketchSheetView;
import com.example.w.tabel.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlobalActivity extends AppCompatActivity implements View.OnClickListener{

    public Calendar c;
    public Person person;
    public Presenter presenter;

    RelativeLayout frTime;
    RelativeLayout frService;
    RelativeLayout frMap;

    View view;
    Button btn1;
    Button btnQR;
    RelativeLayout rl;
    TextView textViewPassword;
    TextView personFIO;
    TextView personBank;

    CalendarFragment calendarFragment;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        int displeyHeight = Resources.getSystem().getDisplayMetrics().heightPixels / 10;

        c = Calendar.getInstance();
        person = new Person();

        initToolbar();
        initCustomView();
        initFrames(displeyHeight);
        initButtonQR(displeyHeight);
        initPresenter();
        initRecycleViewCalendar(displeyHeight, savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if(i == R.id.action_beidg){
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void initPresenter(){
        rl = findViewById(R.id.progress_bar);
        textViewPassword = findViewById(R.id.counter_random);
        presenter = new Presenter(textViewPassword);
        SketchSheetView sketchSheetView = new SketchSheetView(this,100,100,50);
        view = sketchSheetView;
        rl.addView(view);
        presenter.setPb(sketchSheetView);

        btn1 = findViewById(R.id.btn_transparent);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Пароль скопирован в буфер обмена", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initToolbar(){
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        ButtonBarLayout buttonBarLayout = findViewById(R.id.btn_layout);
        buttonBarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        personFIO = findViewById(R.id.mt_name);
        personFIO.setText(person.getPersonFIO());
        personFIO.setTextColor(Color.WHITE);

        personBank = findViewById(R.id.bank);
        personBank.setText(person.getPersonBank());
        personBank.setTextSize(10);
        personBank.setTextColor(Color.WHITE);

        imageView = findViewById(R.id.image_view_photo);
        Bitmap val = BitmapFactory.decodeResource(getResources(), R.drawable.dn_dag);
        RoundedBitmapDrawable var = RoundedBitmapDrawableFactory.create(getResources(), val);

        var.setCircular(true);

        imageView.setImageDrawable(var);
    }

    public void initRecycleViewCalendar(int displeyHeight, Bundle savedInstanceState){
        frMap = findViewById(R.id.calendar_part);
        frMap.getLayoutParams().height = displeyHeight * 5 + displeyHeight / 2 + displeyHeight / 4;

        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            calendarFragment = new CalendarFragment();
            calendarFragment.init(person,c);
            fragmentTransaction.add(R.id.calendar_part, calendarFragment, "CalendarFragment");
            fragmentTransaction.commit();
        }
    }

    public void initFrames(int displeyHeight){
        frTime = findViewById(R.id.work_time);
        frTime.getLayoutParams().height = displeyHeight + displeyHeight / 4;

        frService = findViewById(R.id.second_part);
        frService.getLayoutParams().height = displeyHeight;
    }

    public void initButtonQR(int displeyHeight){
        btnQR = findViewById(R.id.btn_qr);
        btnQR.getLayoutParams().height = displeyHeight;
        btnQR.setOnClickListener(this);
        btnQR.setTextColor(Color.WHITE);
    }

    public void initCustomView(){
        CustomView customView = findViewById(R.id.firstCustom);
        customView.bindView("Начало", "--:--");
        CustomView customView1 = findViewById(R.id.second_custom);
        customView1.bindView("На работе", "--:--");
        CustomView customView2 = findViewById(R.id.third_custom);
        customView2.bindView("Конец", "--:--");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}
