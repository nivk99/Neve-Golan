package com.example.myapplication.userUpdate;

import static com.example.myapplication.userUpdate.model_Teacher_update.update_teacher_card;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.userCard.TeacherCardActivity;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.model.FirebaseModelTeacher;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UpdateTeacher extends AppCompatActivity  {

    //Teacher definition
    private TextView teacher_name_textView;
    private TextView teacher_last_name_textView;
    private TextView teacher_age_textView;
    private TextView teacher_phone_textView;
    private TextView teacher_email_textView;
    private TextView teacher_id_textView;
    private TextView teacher_profession_textView;

    //key
    private String id;

    //Database
    private Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher);
        database=new Database("users/teacher");

        teacher_name_textView=(TextView)findViewById(R.id.editText_teacher_name);
        teacher_last_name_textView=(TextView)findViewById(R.id.editText_teacher_last_name);
        teacher_age_textView=(TextView)findViewById(R.id.editText_teacher_age);
        teacher_phone_textView=(TextView)findViewById(R.id.editText_teacher_phone);
        teacher_email_textView=(TextView)findViewById(R.id.editText_teacher_email);
        teacher_id_textView=(TextView)findViewById(R.id.editText_teacher_id);
        teacher_profession_textView=(TextView)findViewById(R.id.editText_teacher_profession);
        Intent intent=getIntent();
        String[] message=intent.getStringArrayExtra(TeacherCardActivity.MESSAGE_KEY);
        teacher_name_textView.setText(message[0]);
        teacher_last_name_textView.setText(message[1]);
        teacher_age_textView.setText(message[2]);
        teacher_phone_textView.setText(message[3]);
        teacher_email_textView.setText(message[4]);
        teacher_id_textView.setText(message[5]);
        teacher_profession_textView.setText(message[6]);
        id=message[5];
    }

    public void click_teacher_update_teacher(View view)
    {


        String teacher_name_textView_str =teacher_name_textView.getText().toString();
        String teacher_last_name_textView_str=teacher_last_name_textView.getText().toString();
        double teacher_age_textView_str=Double.parseDouble(teacher_age_textView.getText().toString());
        String teacher_phone_textView_str=teacher_phone_textView.getText().toString();
        String teacher_email_textView_str=teacher_email_textView.getText().toString();
        String teacher_id_textView_str=teacher_id_textView.getText().toString();
        String teacher_profession_textView_str= teacher_profession_textView.getText().toString();

        update_teacher_card( teacher_name_textView_str,  teacher_last_name_textView_str,
         teacher_age_textView_str ,  teacher_phone_textView_str ,  teacher_email_textView_str ,
             teacher_id_textView_str ,  teacher_profession_textView_str,  database ,  id);


    }






}