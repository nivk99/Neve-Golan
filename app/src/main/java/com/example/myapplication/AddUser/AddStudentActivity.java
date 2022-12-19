package com.example.myapplication.AddUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.model.FirebaseModelStudent;

public class AddStudentActivity extends AppCompatActivity {

    //Student definition
    private  TextView textView_name,textView_last_name,textView_age,textView_phone,textView_email,textView_id,textView_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
    }


    public void click_student_save_student(View view)
    {

             textView_name=(TextView)(findViewById(R.id.editText_student_name));
             textView_last_name=(TextView)(findViewById(R.id.editText_student_last_name));
             textView_age=(TextView)(findViewById(R.id.editText_student_age));
             textView_phone=(TextView)(findViewById(R.id.editText_student_phone));
             textView_email=(TextView)(findViewById(R.id.editText_student_email));
             textView_id=(TextView)(findViewById(R.id.editText_student_id));
             textView_class=(TextView)(findViewById(R.id.editText_student_class));

             String textView_name_str=textView_name.getText().toString();
             String textView_last_name_str=textView_last_name.getText().toString();

             double textView_age_dub=0;
             try {
                textView_age_dub=Double.parseDouble(textView_age.getText().toString());
             }
             catch (Exception e)
             {
                 textView_age_dub=0;
                 Toast.makeText(this, "הקלד גיל נכון", Toast.LENGTH_LONG).show();
             }

             String textView_phone_str=textView_phone.getText().toString();
             String textView_email_str=textView_email.getText().toString();
             String textView_id_str=textView_id.getText().toString();
             String textView_class_str=textView_class.getText().toString();

            FirebaseModelStudent new_student=new FirebaseModelStudent(textView_name_str,textView_last_name_str,textView_age_dub,textView_phone_str,textView_email_str,textView_id_str,textView_class_str);
            Database database=new Database("users/student");
            database.write_database(new_student);


    }
}