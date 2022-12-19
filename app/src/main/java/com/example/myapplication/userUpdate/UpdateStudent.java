package com.example.myapplication.userUpdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.userCard.StudentCardActivity;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.model.FirebaseModelStudent;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


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


        if(this.id.equals(student_id_textView_str))
        {
            updateStudent update_student=new updateStudent(student_name_textView_str,student_last_name_textView_str,student_age_textView_str,student_phone_textView_str,student_email_textView_str,student_id_textView_str,student_class_textView_str);
            database.update(update_student.toMap(),this.id);

        }
        else
        {
            database.remove(this.id);
            FirebaseModelStudent new_student=new FirebaseModelStudent(student_name_textView_str,student_last_name_textView_str,student_age_textView_str,student_phone_textView_str,student_email_textView_str,student_id_textView_str,student_class_textView_str);
            database.write_database(new_student);
        }


    }




    private static class updateStudent extends FirebaseModelStudent
    {
        public updateStudent(String _name, String _last_name, double _age, String _phone, String _email, String _id, String _class) {
            super(_name, _last_name, _age, _phone, _email, _id, _class);
        }

        public updateStudent() {
        }

        @Exclude
        public Map<String, Object> toMap()
        {
            HashMap<String,Object> result =new HashMap<>();
            result.put("_name",this._name);
            result.put("_last_name",this._last_name);
            result.put("_age",this._age);
            result.put("_phone",this._phone);
            result.put("_email",this._email);
            result.put("_id",this._id);
            result.put("_class",this._class);
            return result;

        }
    }

}