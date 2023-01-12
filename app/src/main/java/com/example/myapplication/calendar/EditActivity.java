package com.example.myapplication.calendar;

import static com.example.myapplication.calendar.Calender_Model.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.menu.ClientMenuActivity;
import com.example.myapplication.model.FirebaseModelActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {
    String year,month,day,name,start,end, Id;
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
        Id = data.getStringExtra("ID");
        String teacher_id = ClientMenuActivity.get();
        // need to check what is the id of admin and what to write in the data base in case of admin write activity
        final boolean accept [] = new boolean[1];
       accept[0] = ( (Id.equals(teacher_id)) || teacher_id.equals("מנהל" ));
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
                if(! accept[0]) {
                    Toast.makeText(EditActivity.this, "אין הרשאה למחיקה הפעילות - פנה למנהל", Toast.LENGTH_SHORT).show();
                }
                else{
                    new Calender_Model().delete_Activity(year+"/"+month+"/"+day+"/"+name+","+start+","+end,getApplicationContext());
                }
            }
        });


        //active "עדכן" button
        findViewById(R.id.buttonUpdateActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = ((EditText) findViewById(R.id.updateActivityName)).getText().toString();
                start = ((EditText) findViewById(R.id.updateTimeStart)).getText().toString();
                end = ((EditText) findViewById(R.id.updateTimeEnd)).getText().toString();
                if(! accept[0]){
                    Toast.makeText(EditActivity.this, "אין הרשאה לעריכת הפעילות - פנה למנהל", Toast.LENGTH_SHORT).show();
                }
                else{
                    myRef.setValue(null);
                    myRef = FirebaseDatabase.getInstance().getReference("activity/"+Integer.parseInt(year)+"/"+Integer.parseInt(month)+"/"+Integer.parseInt(day)).child(name+","+start+","+end);
                    if(teacher_id.equals("מנהל"))Id = "מנהל";
                    myRef.setValue(new FirebaseModelActivity(name,start,end,Id));
                    //Add_new_activity(year, month,day, name , start, end);
                    Toast.makeText(EditActivity.this, "הפעילות עודכנה", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}

