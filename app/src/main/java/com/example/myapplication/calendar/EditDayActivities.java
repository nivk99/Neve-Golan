package com.example.myapplication.calendar;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.Var;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class EditDayActivities extends AppCompatActivity {
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

                if (Add_ActivityName.length() != 0 && Add_TimeEnd.length() != 0 && Add_TimeStart.length() != 0) {
                    //add to firebase,and to do more checks
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Query nameQuery = ref.child("activity").child(year).child(month).child(day).orderByChild("name");
                    nameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        Boolean flag=false;
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            flag=false;
                            for (DataSnapshot nameSnapshot: dataSnapshot.getChildren()) {
                                String save_name=(String)((Map)nameSnapshot.getValue()).get("name");
                                if(save_name.equals(Add_ActivityName)){
                                    Toast.makeText(EditDayActivities.this, "פעילות זאת כבר קיימת", Toast.LENGTH_SHORT).show();
                                    flag=true; break;
                                }
                            }
                            if(!flag){

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("activity/"+year+"/"+month+"/"+day).child(Add_ActivityName+","+Add_TimeStart+","+Add_TimeEnd);
                                myRef.setValue(new Activity(Add_ActivityName,Add_TimeStart,Add_TimeEnd));
                                Toast.makeText(EditDayActivities.this, "הפעילות נוספה", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());
                        }
                    });
                }
                else {
                    Toast.makeText(EditDayActivities.this, "נא למלא את כל השדות", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public String removeSpaces(String s){
        if(s.length()==0)
            return s;
        int i=0;      // remove start spaces(if there are)
        while(s.charAt(i)==' '){
            i++;
        }
        return s.substring(i);
    }
}