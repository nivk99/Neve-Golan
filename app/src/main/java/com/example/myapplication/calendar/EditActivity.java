package com.example.myapplication.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.FirebaseModelActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {
    String year,month,day,name,start,end;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent data=getIntent();
        year=data.getStringExtra("year");
        month=data.getStringExtra("month");
        day=data.getStringExtra("day");
        name=data.getStringExtra("name");
        start=data.getStringExtra("start");
        end=data.getStringExtra("end");

        //set the activity name and time
        ((EditText)findViewById(R.id.updateActivityName)).setText(name);
        ((EditText)findViewById(R.id.updateTimeStart)).setText(start);
        ((EditText)findViewById(R.id.updateTimeEnd)).setText(end);
        String activity_id=name+","+start+","+end;
        myRef = FirebaseDatabase.getInstance().getReference("activity/"+year+"/"+month+"/"+day).child(name+","+start+","+end);

        //delete activity
        findViewById(R.id.buttonDeleteActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.setValue(null);
                Toast.makeText(EditActivity.this, "הפעילות נמחקה", Toast.LENGTH_SHORT).show();

                //jump back to the calander
            }
        });

        //active "עדכן" button
        findViewById(R.id.buttonUpdateActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = ((EditText) findViewById(R.id.updateActivityName)).getText().toString();
                start = ((EditText) findViewById(R.id.updateTimeStart)).getText().toString();
                end = ((EditText) findViewById(R.id.updateTimeEnd)).getText().toString();

                myRef.setValue(null);
                myRef = FirebaseDatabase.getInstance().getReference("activity/"+year+"/"+month+"/"+day).child(name+","+start+","+end);
                myRef.setValue(new FirebaseModelActivity(name,start,end));
                Toast.makeText(EditActivity.this, "הפעילות עודכנה", Toast.LENGTH_SHORT).show();


            }
        });
    }
}

