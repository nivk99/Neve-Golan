package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
    }


    public void click_student_save_student(View view)
    {

        TextView textView_name=(TextView)(findViewById(R.id.editText_student_name));
        TextView textView_last_name=(TextView)(findViewById(R.id.editText_student_last_name));
        TextView textView_age=(TextView)(findViewById(R.id.editText_student_age));
        TextView textView_phone=(TextView)(findViewById(R.id.editText_student_phone));
        TextView textView_email=(TextView)(findViewById(R.id.editText_student_email));
        TextView textView_address=(TextView)(findViewById(R.id.editText_student_address));
        TextView textView_class=(TextView)(findViewById(R.id.editText_student_class));
        student new_student=new student(textView_name.getText().toString(),textView_last_name.getText().toString(),Double.parseDouble(textView_age.getText().toString()),textView_phone.getText().toString(),textView_email.getText().toString(),textView_address.getText().toString(),textView_class.getText().toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users/student").push();
        myRef.setValue(new_student);

    }
}