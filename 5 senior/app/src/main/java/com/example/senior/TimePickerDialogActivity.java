package com.example.senior;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerDialogActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    private TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_dialog);

        tv_time = (TextView) findViewById(R.id.tv_time);
        findViewById(R.id.btn_time).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_time) {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog dialog = new TimePickerDialog(this, this,
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            dialog.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String desc = String.format("您选择的时间是%d时%d分", hourOfDay, minute);
        tv_time.setText(desc);
    }
}