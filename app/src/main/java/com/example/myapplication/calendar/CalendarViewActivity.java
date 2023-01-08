package com.example.myapplication.calendar;

import static com.example.myapplication.calendar.Calender_Model.Display_Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ActivityAdapter;
import com.example.myapplication.adapter.interfaceSelectListener.InterfaceSelectActivityListener;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.model.FirebaseModelActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarViewActivity extends AppCompatActivity implements  InterfaceSelectActivityListener {
    String Year, Month, Day;
    private  Database database;
    private ActivityAdapter adapter;
    private InterfaceSelectActivityListener listener = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Year= String.valueOf(Integer.parseInt(dateFormat.format(date).substring(0,4)));
        Month=String.valueOf(Integer.parseInt(dateFormat.format(date).substring(5,7)));
        Day=String.valueOf(Integer.parseInt(dateFormat.format(date).substring(8)));

        String path = "activity/"+Integer.parseInt(Year)+ "/"+Integer.parseInt(Month)+"/"+Integer.parseInt(Day);
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_list_act);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Display_Activity(path, adapter, database, listener, recyclerView);
        CalendarView calendar=(CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int Yeart=year, Montht=month+1, Dayt=dayOfMonth;
                Year=Integer.toString(year);Month=Integer.toString(month+1);Day=Integer.toString(dayOfMonth);
                String path = "activity/"+Yeart + "/" + Montht + "/" + Dayt;
                final RecyclerView recyclerView =findViewById(R.id.recyclerview_list_act);
                recyclerView.setAdapter(adapter);
                Display_Activity(path, adapter, database, listener,recyclerView);
            }
        });
        // move to page "הוספת פעילויות"
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CalendarViewActivity.this, AddActivity.class);
                intent.putExtra("year",Year);
                intent.putExtra("month", Month);
                intent.putExtra("day", (Day));
                startActivity(intent);

            }
        });

    }

    @Override
    public void onItemClicked(FirebaseModelActivity activity) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("year", Year);
        intent.putExtra("month",Month);
        intent.putExtra("day", Day);
        intent.putExtra("name",activity.getName());
        intent.putExtra("start",activity.getTimeStart());
        intent.putExtra("end",activity.getTimeEnd());
        intent.putExtra("ID",activity.getID());
        startActivity(intent);
    }

}