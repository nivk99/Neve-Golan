package com.example.myapplication.writeUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.Student;

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
            TextView textView_id=(TextView)(findViewById(R.id.editText_student_id));
            TextView textView_class=(TextView)(findViewById(R.id.editText_student_class));
            Student new_student=new Student(textView_name.getText().toString(),textView_last_name.getText().toString(),Double.parseDouble(textView_age.getText().toString()),textView_phone.getText().toString(),textView_email.getText().toString(),textView_id.getText().toString(),textView_class.getText().toString());
            Database database=new Database("users/student");
            database.write_database(new_student);


    }
}