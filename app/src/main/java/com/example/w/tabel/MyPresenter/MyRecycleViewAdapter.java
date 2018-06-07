package com.example.w.tabel.MyPresenter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.w.tabel.Fragment.CalendarFragment;
import com.example.w.tabel.Fragment.DayInfoFragment;
import com.example.w.tabel.Model.Person;
import com.example.w.tabel.MyPresenter.Custom.Calendar.CustomCalendarView;
import com.example.w.tabel.R;

import java.util.Calendar;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {

    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private MyRecycleViewAdapter.ItemClickListener mClickListener;
    MonthWorker monthWorker;
    Person person;
    Calendar c;
    FragmentManager fm;
    CalendarFragment fm1;
    SketchSheetViewCalendar sketchSheetView;

    public MyRecycleViewAdapter(Context context, Calendar calendar, Person person, FragmentManager fragmentManager){
        this.mInflater = LayoutInflater.from(context);
        monthWorker = new MonthWorker(calendar);
        this.c = calendar;
        mData = monthWorker.getMonthInfo();
        sketchSheetView = new SketchSheetViewCalendar(context);
        this.person = person;
        this.fm = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_calendar, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = mData[position];

        int ckeck = 0;
        if(position < monthWorker.firstDayOfMonty()){
            holder.itemView.setVisibility(View.GONE);
        }else{

            ckeck = Integer.valueOf(person.getDayStatus(position - monthWorker.firstDayOfMonty(), c));
            holder.myTextView.setText(data);
            holder.customCalendarView.sketchSheetViewCalendar.changeColor(ckeck);

            if( ckeck== 1){
                holder.myTextView.setTextColor(Color.WHITE);
            }else if(ckeck == 2){
                holder.myTextView.setTextColor(Color.RED);
            }else if (ckeck == 3){
                holder.myTextView.setTextColor(Color.RED);
            }else{
                holder.myTextView.setTextColor(Color.GRAY);
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.mData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView myTextView;
        CustomCalendarView customCalendarView;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.info_text);
            customCalendarView = itemView.findViewById(R.id.custom_id_calendar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
            String date = "";
            //Toast.makeText(myTextView.getContext(),"" + getAdapterPosition(),Toast.LENGTH_SHORT).show();
            date = String.valueOf(myTextView.getText()) + "." + c.get(Calendar.MONTH) + "."
                              + c.get(Calendar.YEAR);

            DayInfoFragment dayInfoFragment  = DayInfoFragment.newInstance(date);
            fm.beginTransaction().replace(R.id.calendar_part, dayInfoFragment, "DayInfoFragment").
                    addToBackStack(null).
                    commit();
        }
    }

    public class MonthWorker{

        String[] data;
        Calendar c;
        int trueDayOfWeek;

        MonthWorker(Calendar calendar){
            c = calendar;
            mainWork();
        }

        private void mainWork(){
            c.set(Calendar.DAY_OF_MONTH, 1);
            int dayOfweek = c.get(Calendar.DAY_OF_WEEK);
            trueDayOfWeek = dayOfWeekCheck(dayOfweek);


            data = new String[c.getActualMaximum(Calendar.DAY_OF_MONTH) + trueDayOfWeek];
            for(int i = 0; i < c.getActualMaximum(Calendar.DAY_OF_MONTH) + trueDayOfWeek; i++ ){
                if(i < trueDayOfWeek){
                    this.data[i] = "";
                }else{
                    this.data[i] = "" + (i - trueDayOfWeek + 1);
                }
            }
        }

        public int firstDayOfMonty(){
            return this.trueDayOfWeek;
        }

        public String[] getMonthInfo(){
            return this.data;
        }

        public int dayOfWeekCheck(int i){
            int tmp = 1;
            if(i == 1){
                tmp = 6; // Saturday
            }else if(i == 2){
                tmp = 0; //Monday and etc
            }else if(i == 3){
                tmp = 1;
            }else if(i == 4){
                tmp = 2;
            }else if(i == 5){
                tmp = 3;
            }else if(i == 6){
                tmp = 4;
            }else if(i == 7){
                tmp = 5;
            }

            return tmp;
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public String getMonthName(){

        String month = creatingMonth(c);
        return month;
    }

    public void dataChange(Calendar c){
        this.monthWorker.c = c;
        monthWorker.mainWork();
        mData = monthWorker.getMonthInfo();
        notifyDataSetChanged();

        String month = creatingMonth(c);
        fm1 = (CalendarFragment) fm.findFragmentByTag("CalendarFragment");
        fm1.changeMonthOnCalendar(month);

        fm1.makeBtnNextMonthInvisible(chekingNextMonthInFuture());
    }

    private String creatingMonth(Calendar c){
        String str = "";
        int monthCount = c.get(Calendar.MONTH);
        int yearCount = c.get(Calendar.YEAR);

        if(monthCount == 11){
            str = "Декабрь";
        }else if(monthCount == 10){
            str = "Ноябрь";
        }else if(monthCount == 9){
            str = "Октябрь";
        }else if(monthCount == 8){
            str = "Сентябрь";
        }else if(monthCount == 7){
            str = "Август";
        }else if(monthCount == 6){
            str = "Июль";
        }else if(monthCount == 5){
            str = "Июнь";
        }else if(monthCount == 4){
            str = "Май";
        }else if(monthCount == 3){
            str = "Апрель";
        }else if(monthCount == 2){
            str = "Март";
        }else if(monthCount == 1){
            str = "Февраль";
        }else if(monthCount == 0){
            str = "Январь";
        }

        if(yearCount == 2018){
            str += " 2018";
        }else if(yearCount == 2017){
            str += " 2017";
        }else if(yearCount == 2016){
            str += " 2016";
        }else if(yearCount == 2015){
            str += " 2015";
        }else if(yearCount == 2019){
            str += " 2019";
        }

        return str;
    }

    private int chekingNextMonthInFuture(){
        Calendar b = Calendar.getInstance();

        if (b.get(Calendar.YEAR) == c.get(Calendar.YEAR)) {
            if(b.get(Calendar.MONTH) - c.get(Calendar.MONTH) == -1){
                return 1;
            }else {
                return 0;
            }
        }
        return 0;
    }

    public String getItem(int id) {
        return mData[id];
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
