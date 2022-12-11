package com.example.myapplication.personalInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.Teacher;

public class Personal_Information_teacher extends AppCompatActivity {
    public TextView teacher_name_textView;
    public TextView teacher_last_name_textView;
    public TextView teacher_age_textView;
    public TextView teacher_phone_textView;
    public TextView teacher_email_textView;
    public TextView teacher_id_textView;
    public TextView teacher_profession_textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information_teacher);
        teacher_name_textView=(TextView)findViewById(R.id.textView_name_teacher);
        teacher_last_name_textView=(TextView)findViewById(R.id.textView_last_name_teacher);
        teacher_age_textView=(TextView)findViewById(R.id.textView_age_teacher);
        teacher_phone_textView=(TextView)findViewById(R.id.textView_phone_teacher);
        teacher_email_textView=(TextView)findViewById(R.id.textView_email_teacher);
        teacher_id_textView=(TextView)findViewById(R.id.textView_id_teacher);
        teacher_profession_textView=(TextView)findViewById(R.id.textView_profession_teacher);
        Database database=new Database("users/teacher");
        database.get_teacher(Authenticate.get_current_email(),this);


    }
}