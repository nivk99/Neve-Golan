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

        if(student.isChecked())
        {
            student();
        }
        else if(teacher.isChecked())
        {
            teacher();
        }
         else if(all.isChecked())
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