package com.example.w.tabel.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.w.tabel.Model.Person;
import com.example.w.tabel.MyPresenter.MyRecycleViewAdapter;
import com.example.w.tabel.R;

import java.util.Calendar;

public class CalendarFragment extends Fragment implements MyRecycleViewAdapter.ItemClickListener{

    Calendar c;
    Person person;
    MyRecycleViewAdapter adapter;
    ArrayAdapter<String> arrayAdapter;

    GridView gridView;
    RecyclerView rv;
    ImageButton tv1;
    static TextView tv2;
    static ImageButton tv3;
    String[] weekDays;

    public CalendarFragment() {
        Log.d("CalendarFragment", "CalendarFragment");
    }

    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        weekDays = new String[]{"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вск"};
    }

    public void init(Person p, Calendar c){
        this.person = p;
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        rv = view.findViewById(R.id.rvNumbers);
        gridView = view.findViewById(R.id.grid_view);
        gridView.setClickable(false);

        tv1 = view.findViewById(R.id.left_btn);
        tv2 = view.findViewById(R.id.center_btn);
        tv3 = view.findViewById(R.id.right_btn_2);

        arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.item_grid, R.id.tvText, weekDays);
        gridView.setNumColumns(7);
        gridView.setAdapter(arrayAdapter);
        rv.setLayoutManager(new GridLayoutManager(view.getContext(), 7));
        adapter = new MyRecycleViewAdapter(view.getContext(), c, person, getFragmentManager());
        adapter.setClickListener(this);
        rv.setAdapter(adapter);

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int monthChange = c.get(Calendar.MONTH);
                monthChange++;
                c.set(Calendar.MONTH, monthChange);
                adapter.dataChange(c);
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int monthChange = c.get(Calendar.MONTH);
                monthChange--;
                c.set(Calendar.MONTH, monthChange);
                adapter.dataChange(c);
            }
        });


        tv2.setText(adapter.getMonthName());
        return view;
    }

    public void changeMonthOnCalendar(String str){
        tv2.setText(str);
    }

    public void makeBtnNextMonthInvisible(int flag){
        if(flag == 1){
            tv3.setVisibility(View.GONE);
        }else{
            tv3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
         String str = person.getDayStatus(Integer.valueOf(adapter.getItem(position)) - 1);
    }
}
