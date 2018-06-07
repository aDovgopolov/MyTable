package com.example.w.tabel.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.w.tabel.MyPresenter.Custom.Calendar.CustomCalendarView;
import com.example.w.tabel.R;

public class DayInfoFragment extends Fragment implements View.OnClickListener{

    ImageButton left_btn_undo;
    TextView center_day_info;
    ImageButton right_alert_dialog;

    TextView txt_enter_time;
    TextView txt_exit_time;
    CustomCalendarView customCViewDayInfo;

    String day = "";

    public DayInfoFragment() {
    }

    public static DayInfoFragment newInstance(String dayDate) {
        DayInfoFragment fragment = new DayInfoFragment();
        Bundle args = new Bundle();
        args.putString("day", dayDate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        day = getArguments().getString("day");
        if(savedInstanceState != null){

            day = getArguments().getString("day");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_info, container, false);

        left_btn_undo = view.findViewById(R.id.left_btn_undo);
        left_btn_undo.setOnClickListener(this);

        right_alert_dialog = view.findViewById(R.id.right_alert_dialog);
        initMainAlertDialog(right_alert_dialog);

        center_day_info = view.findViewById(R.id.center_day_info);
        center_day_info.setText(day);

        customCViewDayInfo = view.findViewById(R.id.custom_id_calendar_second_ft);
        customCViewDayInfo.sketchSheetViewCalendar.changeColor(1);

        txt_enter_time = view.findViewById(R.id.txt_enter_time);
        txt_exit_time = view.findViewById(R.id.txt_exit_time);
        return view;
    }

    public void initMainAlertDialog(ImageButton right_alert_dialog){

        right_alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(view.getContext());
                View mainDialogView = getLayoutInflater().inflate(R.layout.alert_dialog_main, null);

                Button btn_continue = mainDialogView.findViewById(R.id.btn_continue);
                Button btn_cancel = mainDialogView.findViewById(R.id.btn_cancel);

                final EditText edit_txt_pin = mainDialogView.findViewById(R.id.edit_txt_pin);
                edit_txt_pin.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorTRUEBLACK), PorterDuff.Mode.SRC_ATOP);

                ImageButton btn_dialpad = mainDialogView.findViewById(R.id.btn_dialpad);
                btn_dialpad.setBackgroundColor(Color.TRANSPARENT);
                btn_dialpad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        initUpeerAlertDialog(view.getContext());
                        Log.d("FOCUS", "HERE");
                    }
                });


                final TextView tx_password_mini = mainDialogView.findViewById(R.id.tx_password_mini);

                final TextView tx_remind = mainDialogView.findViewById(R.id.tx_remind);

                edit_txt_pin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {
                        if (hasFocus){
                            edit_txt_pin.getBackground().mutate().setColorFilter(getResources().getColor(R.color.privat), PorterDuff.Mode.SRC_ATOP);
                            edit_txt_pin.setHint("");
                            tx_password_mini.setVisibility(View.VISIBLE);
                        }
                        else
                            edit_txt_pin.setHint("Пароль");
                            Log.d("FOCUS", "FOCUS here");
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setView(mainDialogView);

                final AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();


                btn_continue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edit_txt_pin.getText().toString().isEmpty()){
                            Log.d("AlertDialog", "HERE = " + edit_txt_pin.getText() + ", " + View.VISIBLE);
                            tx_remind.setVisibility(View.VISIBLE);
                            edit_txt_pin.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorred), PorterDuff.Mode.SRC_ATOP);
                        }else{
                            alertDialog.dismiss();
                            Toast.makeText(view.getContext(), "Perfect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
    }

    public void initUpeerAlertDialog(Context context){

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                final View dialogView = getLayoutInflater().inflate(R.layout.alert_dialog_upper, null);


                final EditText editTextPin = dialogView.findViewById(R.id.edit_txt_pin);
                editTextPin.getBackground().mutate().setColorFilter(getResources().getColor(R.color.privat), PorterDuff.Mode.SRC_ATOP);
                final TextView tv_piv_remind = dialogView.findViewById(R.id.tv_piv_remind);

                builder.setMessage("Используйте пин-код для подтвердения личности")
                        .setCancelable(false)
                        .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("ОТМЕНА", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });


                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                int color = ContextCompat.getColor(dialogView.getContext(), R.color.privat);

                alertDialog.show();

                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(editTextPin.getText().toString().isEmpty()){
                            Log.d("AlertDialog", "HERE = " + editTextPin.getText() + ", " + View.VISIBLE);

                            tv_piv_remind.setVisibility(View.VISIBLE);
                        }else{
                            alertDialog.dismiss();
                            Toast.makeText(view.getContext(), "Perfect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(color);
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(color);
            }
        });
    }

    @Override
    public void onClick(View view) {

        Fragment fragment = getFragmentManager().findFragmentByTag("CalendarFragment");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.calendar_part, fragment)
                .commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
