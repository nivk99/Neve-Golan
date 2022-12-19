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
        String path = "activity/"+dateFormat.format(date);
        Year=dateFormat.format(date).substring(0,4);
        Month=dateFormat.format(date).substring(5,7);
        Day=dateFormat.format(date).substring(8);

        if ( path.charAt(path.length()-2)=='0')
            path = path.substring(0,path.length()-2)+path.substring(path.length()-1);
        //String path = "activity/2022/12/9";
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_list_act);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<FirebaseModelActivity> activitis=new ArrayList<>();
        adapter =new ActivityAdapter(activitis,new FirebaseModelActivity(),this);

        recyclerView.setAdapter(adapter);
        database = new Database(path);
        database.read_database_activity(adapter);

        CalendarView calendar=(CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int Yeart=year, Montht=month+1, Dayt=dayOfMonth;
                Year=Integer.toString(year);Month=Integer.toString(month+1);Day=Integer.toString(dayOfMonth);
                String path = "activity/"+Yeart + "/" + Montht + "/" + Dayt;
                ArrayList<FirebaseModelActivity> activitis=new ArrayList<>();
                adapter =new ActivityAdapter(activitis,new FirebaseModelActivity(),listener);
                final RecyclerView recyclerView =findViewById(R.id.recyclerview_list_act);
                recyclerView.setAdapter(adapter);
                database = new Database(path);
                database.read_database_activity(adapter);
            }
        });
        // move to page "הוספת פעילויות"
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CalendarViewActivity.this, EditDayActivities.class);
                intent.putExtra("year",Year);
                intent.putExtra("month", Month);
                intent.putExtra("day", (Day));
                startActivity(intent);

            }
        });

    }
    public void ClickToRemov(View view){
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
    public void onItemClicked(FirebaseModelActivity activity) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("year", Year);
        intent.putExtra("month",Month);
        intent.putExtra("day", Day);
        intent.putExtra("name",activity.getName());
        intent.putExtra("start",activity.getTimeStart());
        intent.putExtra("end",activity.getTimeEnd());
        startActivity(intent);
    }

}