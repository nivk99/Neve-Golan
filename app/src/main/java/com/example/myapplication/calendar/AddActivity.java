package com.example.myapplication.calendar;

import static android.content.ContentValues.TAG;

import static com.example.myapplication.calendar.Calender_Model.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.menu.ClientMenuActivity;
import com.example.myapplication.model.FirebaseModelActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    String Delete_ActivityName="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_day_activities);

        Intent data=getIntent();
        String year=data.getStringExtra("year");
        String month=data.getStringExtra("month");
        String day=data.getStringExtra("day");

        // when click on "הוסף" save the activity to firebase
        findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Add_ActivityName =removeSpaces( ((EditText) findViewById(R.id.AddActivityName)).getText().toString());
                String Add_TimeStart =removeSpaces( ((EditText) findViewById(R.id.AddTimeStart)).getText().toString());
                String Add_TimeEnd =removeSpaces( ((EditText) findViewById(R.id.AddTimeEnd)).getText().toString());
                String uid = ClientMenuActivity.get();

                if (Add_ActivityName.length() != 0 && Add_TimeEnd.length() != 0 && Add_TimeStart.length() != 0) {
                    //add to firebase,and to do more checks
                    new Calender_Model().Add_new_activity(year, month, day,Add_ActivityName, Add_TimeStart, Add_TimeEnd,uid,getApplicationContext());
                }
                else {
                    Toast.makeText(AddActivity.this, "נא למלא את כל השדות", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

    public void click_time_start(View view)
    {
        Calendar systemCalender=Calendar.getInstance();
        int hour=systemCalender.get(Calendar.HOUR_OF_DAY);
        int minute=systemCalender.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener()
        {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                 ((EditText) findViewById(R.id.AddTimeStart)).setText(hourOfDay+":"+minute);
            }
        }
                ,hour,minute,true);
        timePickerDialog.show();

    }
    public void click_time_end(View view)
    {
        Calendar systemCalender=Calendar.getInstance();
        int hour=systemCalender.get(Calendar.HOUR_OF_DAY);
        int minute=systemCalender.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener()
        {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                ((EditText) findViewById(R.id.AddTimeEnd)).setText(hourOfDay+":"+minute);
            }
        }
                ,hour,minute,true);
        timePickerDialog.show();

    }

    // used for make toast from other class
    public static void toasty(Context con, String msg)
    {
        Toast.makeText(con, msg, Toast.LENGTH_LONG).show();
    }


}