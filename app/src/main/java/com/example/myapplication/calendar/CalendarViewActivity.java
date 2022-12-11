package com.example.myapplication.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ActivityAdapter;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.Student;
import com.example.myapplication.users.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarViewActivity extends AppCompatActivity implements InterfaceActivity {
    int Year=0, Month=0, Day=0;
    private  Database database;
    private ActivityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        //@SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //Date date = new Date();
        //String path = "activity/"+dateFormat.format(date);
        String path = "activity/2022/12/9";
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_list_act);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<Activity> activitis=new ArrayList<>();
        adapter =new ActivityAdapter(activitis,new Activity());

        recyclerView.setAdapter(adapter);
        database = new Database(path);
        database.read_database_activity(adapter,this);

        CalendarView calendar=(CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String message = "Selected Date " +year+"/"+(month+1)+"/"+dayOfMonth;
                Toast.makeText(CalendarViewActivity.this, message, Toast.LENGTH_LONG).show();
                Year=year; Month=month+1; Day=dayOfMonth;
                String path = "activity/"+Year + "/" + Month + "/" + Day;
                ArrayList<Activity> activitis=new ArrayList<>();
                adapter =new ActivityAdapter(activitis,new Activity());
                final RecyclerView recyclerView =findViewById(R.id.recyclerview_list_act);
                recyclerView.setAdapter(adapter);
                database = new Database(path);
                database.read_database_activity(adapter,_this());
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

    @Override
    public InterfaceActivity _this() {
        return this;
    }
//    @Override
//    protected void onPause(){
//        super.onPause();
//        Year=0; Month=0; Day=0;
//
//    }
}