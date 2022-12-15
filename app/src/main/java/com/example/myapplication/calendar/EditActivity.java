package com.example.myapplication.calendar;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Database;
import com.google.errorprone.annotations.Var;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {
    String year,month,day,name,start,end;
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
        //delete activity
        findViewById(R.id.buttonDeleteActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query nameQuery = ref.child("activity").child(year).child(month).child(day).orderByKey().equalTo(activity_id);

                nameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot nameSnapshot : dataSnapshot.getChildren()) {
                            nameSnapshot.getRef().removeValue();
                        }
                        Toast.makeText(EditActivity.this, "הםעילות נמחקה", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });

                //jump back to the calander
            }
        });

        //active "עדכן" button
        findViewById(R.id.buttonUpdateActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp_name = ((EditText) findViewById(R.id.updateActivityName)).getText().toString();
                start = ((EditText) findViewById(R.id.updateTimeStart)).getText().toString();
                end = ((EditText) findViewById(R.id.updateTimeEnd)).getText().toString();
                HashMap<String, Object> update = new HashMap<>();
                update.put("name", name);
                update.put("start", start);
                update.put("end", end);

                Database database=new Database("activity/"+year+"/"+month+"/"+day);
                database.update(update,activity_id);
            }
        });
    }
}

