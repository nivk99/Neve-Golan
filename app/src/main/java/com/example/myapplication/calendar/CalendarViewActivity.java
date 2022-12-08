package com.example.myapplication.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.myapplication.R;

public class CalendarViewActivity extends AppCompatActivity {
    int Year=0, Month=0, Day=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        CalendarView calendar=(CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String message = "Selected Date " +year+"/"+(month+1)+"/"+dayOfMonth;
                Toast.makeText(CalendarViewActivity.this, message, Toast.LENGTH_LONG).show();
                Year=year; Month=month+1; Day=dayOfMonth;
            }
        });
    // move to page "עריכת פעילויות"
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Year==0||Day==0||Month==0){
                    Toast.makeText(CalendarViewActivity.this, "please choose a day", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(CalendarViewActivity.this, EditDayActivities.class);
                    intent.putExtra("year", Integer.toString(Year));
                    intent.putExtra("month", Integer.toString(Month));
                    intent.putExtra("day", Integer.toString(Day));
                    startActivity(intent);
                }
            }
        });

    }
//    @Override
//    protected void onPause(){
//        super.onPause();
//        Year=0; Month=0; Day=0;
//
//    }
}