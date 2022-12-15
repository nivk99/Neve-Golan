package com.example.myapplication.updateRemoveUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.readUser.admin.AdminContactListTeacherActivity;
import com.example.myapplication.users.FirebaseModelTeacher;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UpdateRemoveTeacher extends AppCompatActivity  {

    public TextView teacher_name_textView;
    public TextView teacher_last_name_textView;
    public TextView teacher_age_textView;
    public TextView teacher_phone_textView;
    public TextView teacher_email_textView;
    public TextView teacher_id_textView;
    public TextView teacher_profession_textView;
    String id;
    Database database;
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
        String[] message=intent.getStringArrayExtra(AdminContactListTeacherActivity.MESSAGE_KEY);
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

        if(this.id.equals(teacher_id_textView_str))
        {
            updateTeacher update_teacher=new updateTeacher(teacher_name_textView_str,teacher_last_name_textView_str,teacher_age_textView_str,teacher_phone_textView_str,teacher_email_textView_str,teacher_id_textView_str,teacher_profession_textView_str);
            database.update(update_teacher.toMap(),this.id);

        }
        else
        {
            database.remove(this.id);
            FirebaseModelTeacher new_teacher=new FirebaseModelTeacher(teacher_name_textView_str,teacher_last_name_textView_str,teacher_age_textView_str,teacher_phone_textView_str,teacher_email_textView_str,teacher_id_textView_str,teacher_profession_textView_str);
            database.write_database(new_teacher);
        }

    }


    private static class  updateTeacher extends FirebaseModelTeacher
    {
        public updateTeacher(String _name, String _last_name, double _age, String _phone, String _email, String _id, String _profession) {
            super(_name, _last_name, _age, _phone, _email, _id, _profession);
        }

        public updateTeacher() {
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
            result.put("_profession",this._profession);
            return result;

        }




    }


    public void click_remove_teacher(View view)
    {
        database.remove(this.id);

    }

}