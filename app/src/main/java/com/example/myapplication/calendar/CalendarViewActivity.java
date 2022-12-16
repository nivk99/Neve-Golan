package com.example.myapplication.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ActivityAdapter;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.FirebaseModelStudent;
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
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String path = "activity/"+dateFormat.format(date);
        if ( path.charAt(path.length()-2)=='0')
            path = path.substring(0,path.length()-2)+path.substring(path.length()-1);
        //String path = "activity/2022/12/9";
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
    public void ClickToRemove(View view){
        int ya;
        TextView name_textView=findViewById(R.id.textView_activity_name);
        TextView timeEnd_textView=findViewById(R.id.textView_activity_time_end);
        TextView timeStart_textView=findViewById(R.id.textView_activity_time_start);


        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("year", Year);
        intent.putExtra("month",Month);
        intent.putExtra("day", Day);
        intent.putExtra("name",name_textView.getText().toString());
        intent.putExtra("start",timeStart_textView.getText().toString());
        intent.putExtra("end",timeEnd_textView.getText().toString());
        startActivity(intent);

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