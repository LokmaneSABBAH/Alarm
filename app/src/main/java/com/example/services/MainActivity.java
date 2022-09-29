package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView textViewClock;

    int heure, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewClock = findViewById(R.id.textViewClock);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm aa");
        textViewClock.setText(simpleDateFormat.format(calendar.getTime()));

        heure = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);









    }

    public void btnStart(View view) {
        Intent intent = new Intent(this, MyService.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 31);
        calendar.set(Calendar.SECOND, 00);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 0, pendingIntent);

    }


    public void btnValiderHeure(View view) {
        Intent intent = new Intent(this, MyService.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, heure);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 00);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 0, pendingIntent);

    }

    public void btnChangerHeure(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                heure = i;
                minute = i1;
                Calendar calendar = Calendar.getInstance();
                calendar.set(0, 0, 0, heure, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm aa");
                textViewClock.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, heure, minute, true);
        timePickerDialog.updateTime(heure, minute);
        timePickerDialog.show();
    }



    public void btnArreterHeure(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}