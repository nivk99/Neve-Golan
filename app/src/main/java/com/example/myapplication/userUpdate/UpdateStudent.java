package com.example.myapplication.userUpdate;

import static com.example.myapplication.userUpdate.model_student_update.Update_student_card;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.userCard.StudentCardActivity;
import com.example.myapplication.firebase.Database;


public class UpdateStudent extends AppCompatActivity {


    //student definition
    private TextView student_name_textView;
    private TextView student_last_name_textView;
    private TextView student_age_textView;
    private TextView student_phone_textView;
    private TextView student_email_textView;
    private TextView student_id_textView;
    private TextView student_class_textView;


    private  String id;

    private Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);


        database=new Database("users/student");

        student_name_textView=(TextView)findViewById(R.id.editText_student_name);
        student_last_name_textView=(TextView)findViewById(R.id.editText_student_last_name);
        student_age_textView=(TextView)findViewById(R.id.editText_student_age);
        student_phone_textView=(TextView)findViewById(R.id.editText_student_phone);
        student_email_textView=(TextView)findViewById(R.id.editText_student_email);
        student_id_textView=(TextView)findViewById(R.id.editText_student_id);
        student_class_textView=(TextView)findViewById(R.id.editText_student_class);

        Intent intent=getIntent();
        String[] message=intent.getStringArrayExtra(StudentCardActivity.MESSAGE_KEY);
        student_name_textView.setText(message[0]);
        student_last_name_textView.setText(message[1]);
        student_age_textView.setText(message[2]);
        student_phone_textView.setText(message[3]);
        student_email_textView.setText(message[4]);
        student_id_textView.setText(message[5]);
        student_class_textView.setText(message[6]);
        id=message[5];





    }


    public void click_student_Update_student(View view)
    {
        String student_name_textView_str =student_name_textView.getText().toString();
        String student_last_name_textView_str=student_last_name_textView.getText().toString();
        double student_age_textView_str=Double.parseDouble(student_age_textView.getText().toString());
        String student_phone_textView_str=student_phone_textView.getText().toString();
        String student_email_textView_str=student_email_textView.getText().toString();
        String student_id_textView_str=student_id_textView.getText().toString();
        String student_class_textView_str=student_class_textView.getText().toString();

        Update_student_card(database, student_name_textView_str,student_last_name_textView_str, student_age_textView_str, student_phone_textView_str, student_email_textView_str ,
                student_id_textView_str, student_class_textView_str, this.id);

    }






}