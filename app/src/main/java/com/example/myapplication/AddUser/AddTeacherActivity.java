package com.example.myapplication.AddUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.FirebaseModelTeacher;

public class AddTeacherActivity extends AppCompatActivity implements InterfaceAddUser {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
    }

    public void click_teacher_save_teacher(View view) {

        TextView textView_name = (TextView) (findViewById(R.id.editText_teacher_name));
        TextView textView_last_name = (TextView) (findViewById(R.id.editText_teacher_last_name));
        TextView textView_age = (TextView) (findViewById(R.id.editText_teacher_age));
        TextView textView_phone = (TextView) (findViewById(R.id.editText_teacher_phone));
        TextView textView_email = (TextView) (findViewById(R.id.editText_teacher_email));
        TextView textView_id = (TextView) (findViewById(R.id.editText_teacher_id));
        TextView textView_profession = (TextView) (findViewById(R.id.editText_teacher_profession));
        FirebaseModelTeacher new_teacher = new FirebaseModelTeacher(textView_name.getText().toString(), textView_last_name.getText().toString(), Double.parseDouble(textView_age.getText().toString()), textView_phone.getText().toString(), textView_email.getText().toString(), textView_id.getText().toString(), textView_profession.getText().toString());
        Database database = new Database("users/teacher");
        database.write_database(new_teacher);
        String email = new_teacher.get_email();
        String password = new_teacher.get_name() + " " + new_teacher.get_last_name();
        Authenticate mAuth = new Authenticate();
        mAuth.register(email, password, this);

    }

    @Override
    public InterfaceAddUser _this() {
        return this;
    }
}