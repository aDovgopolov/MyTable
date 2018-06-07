package com.example.w.tabel.Model;

import android.util.Log;
import java.util.ArrayList;
import java.util.Calendar;

public class Person {

    Calendar yearCalendar;
    private String mySername = "Довгополов";
    private String myname = "Александр";
    private String myLastName = "Геннадьевич";
    private String personBank = "ПриватБанк Украина";
    private String posit_name  = "Аналитик баз данных";
    private int myPin;
    private int messageCount = 0;

    int monthChange;

    private String[] calendarDay = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21",
            "22","23","24","25","26","27","28","29","30", "31"};
    private String[] workTime = {"08:30", "17:30"};
    private String[][] myDay = {{""},{"",""},{""}};

    //private int[] dayStatus = {1,2,3,4}; // 1 - OK, 2 = NOT OK, 3 = country holiday, 4 = weekend

    ArrayList<String[][]> myMonth = new ArrayList<>();
    ArrayList<String[][]> myMonthJune;
    ArrayList<ArrayList<String[][]>> myYear = new ArrayList<>();

    public Person() {
        yearCalendar = Calendar.getInstance();
        initYearInfo(yearCalendar); // generata data to show on main activity
        initAprilMotnh(); // generata data(+ work days) to show on main activity
    }

    public void initYearInfo(Calendar yearCalendar){
        String[][] myDay;
        Calendar ga = yearCalendar;
        monthChange = 4; // dont ask why 4 )
        myMonthJune = new ArrayList<>();

        for(int j = 0; j < 2; j++){
            myMonthJune  = new ArrayList<>();

            ga.set(Calendar.MONTH, monthChange);
            ga.set(Calendar.DAY_OF_MONTH, 1);

            for(int i = 1; i <= ga.getActualMaximum(Calendar.DAY_OF_MONTH); i++){

                myDay = new String[][]{{""}, {"", ""}, {""}};
                myDay[0][0] = calendarDay[i - 1];
                ga.set(Calendar.DAY_OF_MONTH, i);

                    if(ga.get(Calendar.DAY_OF_WEEK) == 1  || ga.get(Calendar.DAY_OF_WEEK) == 7){
                        myDay[2][0] = String.valueOf(4);
                    }else{
                        myDay[2][0] = String.valueOf(5);
                    }
                    myMonthJune.add(myDay);
            }
                myYear.add(myMonthJune);

            monthChange = monthChange + 1;
        }
    }

    public void initAprilMotnh(){
        String[][] myDay;

        for (int i = 0; i < calendarDay.length; i++) {
            myDay = new String[][]{{""}, {"", ""}, {""}};
            myDay[0][0] = calendarDay[i];
            myDay[1][0] = workTime[0];
            myDay[1][1] = workTime[1];

            if (i == 0 || i == 6 || i == 8 || i == 13 || i == 14 || i == 20 || i == 21 || i == 27 || i == 28 || i == 29) {
                myDay[2][0] = String.valueOf(4);
            } else if( i == 7){
                myDay[2][0] = String.valueOf(3);
            } else {
                myDay[2][0] = String.valueOf(1);
            }

            myMonth.add(myDay);}
            myYear.add(myMonth);
    }

    public String getPersonFIO(){
        String str = this.mySername + " " + this.myname.substring(0,1) + ". " + this.myLastName.substring(0,1) + ".";
        return str;
    }

    public String getPersonBank(){
        return this.personBank;
    }

    public String getDayStatus(int index, Calendar textCalendar){
        ArrayList<String[][]> myMonthstatusCheck;
        String[][] tmp = {};
        String tmp1 = "";

        if(textCalendar.get(Calendar.MONTH) == 4){
            Log.d("PERSON", "Calendar = " + textCalendar.get(Calendar.MONTH) + ", ");
            myMonthstatusCheck = myYear.get(textCalendar.get(Calendar.MONTH) - 4);
            tmp = myMonthstatusCheck.get(index);
            tmp1 = tmp[2][0];
        }else if (textCalendar.get(Calendar.MONTH) == 5){
            Log.d("PERSON", "Calendar = " + textCalendar.get(Calendar.MONTH));
            myMonthstatusCheck = myYear.get(textCalendar.get(Calendar.MONTH) - 4);
            tmp = myMonthstatusCheck.get(index);
            tmp1 = tmp[2][0];
        }else if (textCalendar.get(Calendar.MONTH) == 3 && textCalendar.get(Calendar.YEAR) == 2018){
            tmp = myMonth.get(index);
            tmp1 = tmp[2][0];
        }else{
            tmp1 = "5";
        }
        return tmp1;
    }

    public String getDayStatus(int index){
        String[][] tmp = {};
        String tmp1 = "";
        tmp = myMonth.get(index);
        tmp1 = tmp[2][0];
        return tmp1;
    }

    public void textFun(Calendar yearCalendar){
        String[][] myDay;
        myMonthJune  = new ArrayList<>();

        Log.d("initYearInfo", " C = " + yearCalendar );
        for(int i = 1; i <= yearCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            myDay = new String[][]{{""}, {"", ""}, {""}};
            myDay[0][0] = calendarDay[i - 1];

            yearCalendar.set(Calendar.DAY_OF_MONTH, i);
            Log.d("getDayStatus", "getDayStatus = " + yearCalendar);

            if(yearCalendar.get(Calendar.DAY_OF_WEEK) == 1  || yearCalendar.get(Calendar.DAY_OF_WEEK) == 7){
                myDay[2][0] = String.valueOf(4);
            }else{
                myDay[2][0] = String.valueOf(5);
            }

            myMonthJune.add(myDay);
        }
    }

}
