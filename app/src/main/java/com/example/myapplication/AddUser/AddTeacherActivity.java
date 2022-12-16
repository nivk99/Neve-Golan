package com.example.myapplication.AddUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.FirebaseModelTeacher;

public class AddTeacherActivity extends AppCompatActivity implements InterfaceAddUser {

    //Teacher definition
    private TextView textView_name, textView_last_name, textView_age, textView_phone, textView_email, textView_id, textView_profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
    }

    public void click_teacher_save_teacher(View view) {

        textView_name = (TextView) (findViewById(R.id.editText_teacher_name));
        textView_last_name = (TextView) (findViewById(R.id.editText_teacher_last_name));
        textView_age = (TextView) (findViewById(R.id.editText_teacher_age));
        textView_phone = (TextView) (findViewById(R.id.editText_teacher_phone));
        textView_email = (TextView) (findViewById(R.id.editText_teacher_email));
        textView_id = (TextView) (findViewById(R.id.editText_teacher_id));
        textView_profession = (TextView) (findViewById(R.id.editText_teacher_profession));

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
        String textView_profession_str=textView_profession.getText().toString();


        FirebaseModelTeacher new_teacher = new FirebaseModelTeacher(textView_name_str,textView_last_name_str,textView_age_dub,textView_phone_str,textView_email_str,textView_id_str,textView_profession_str);
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