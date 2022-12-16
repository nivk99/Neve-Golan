package com.example.myapplication.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.myapplication.R;

public class sms_users_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_users);
    }

    public void smsSendMessage(View view)
    {
        CheckBox student=(CheckBox)findViewById(R.id.checkBox_student);
        CheckBox  teacher=(CheckBox)findViewById(R.id.checkBox_teacher);
        CheckBox  all=(CheckBox)findViewById(R.id.checkBox_all);

        String key="";

        if(student.isChecked())
        {
            key="student";
        }
        if(teacher.isChecked())
        {
            key="teacher";
        }
        if(all.isChecked())
        {
            key="all";
        }

        if(key.equals("student"))
        {
            student();
        }
        if(key.equals("teacher"))
        {
            teacher();
        }
        if(key.equals("all"))
        {
            all();
        }


    }

    private void student()
    {


    }

    private void teacher()
    {


    }

    private void all()
    {



    }



}