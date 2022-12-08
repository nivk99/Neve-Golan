package com.example.myapplication.writeUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.Teacher;

public class AddTeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
    }

    public void click_teacher_save_teacher(View view)
    {

            TextView textView_name=(TextView)(findViewById(R.id.editText_teacher_name));
            TextView textView_last_name=(TextView)(findViewById(R.id.editText_teacher_last_name));
            TextView textView_age=(TextView)(findViewById(R.id.editText_teacher_age));
            TextView textView_phone=(TextView)(findViewById(R.id.editText_teacher_phone));
            TextView textView_email=(TextView)(findViewById(R.id.editText_teacher_email));
            TextView textView_address=(TextView)(findViewById(R.id.editText_teacher_address));
            TextView textView_profession=(TextView)(findViewById(R.id.editText_teacher_profession));
            Teacher new_teacher=new Teacher(textView_name.getText().toString(),textView_last_name.getText().toString(),Double.parseDouble(textView_age.getText().toString()),textView_phone.getText().toString(),textView_email.getText().toString(),textView_address.getText().toString(),textView_profession.getText().toString());
            Database database=new Database("users/teacher");
            database.write_database(new_teacher);
            String email=new_teacher.get_email();
            String password=new_teacher.get_name()+" "+new_teacher.get_last_name();
            Authenticate mAuth=new Authenticate();
            mAuth.register(email,password,this);

    }
}