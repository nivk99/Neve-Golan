package com.example.myapplication.readUser.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.readUser.InterfaceContactList;
import com.example.myapplication.updateRemoveUser.UpdateRemoveTeacher;
import com.example.myapplication.users.Teacher;
import com.example.myapplication.users.User;
import com.example.myapplication.writeUser.AddTeacherActivity;

import java.util.ArrayList;

public class AdminContactListTeacherActivity extends AppCompatActivity implements InterfaceContactList {

    private  Database database;
    private UserAdapter adapter;

    public static final String MESSAGE_KEY="com.example.myapplication.readUser.ContactListTeacherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_contact_list_teacher);
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_admin_teacher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<User> users_teacher=new ArrayList<>();
        adapter =new UserAdapter(users_teacher,new Teacher());
        recyclerView.setAdapter(adapter);
        database=new Database("users/teacher");
        database.read_database(adapter,this);

    }


    public void click_add_teacher(View view)
    {
        startActivity(new Intent(this, AddTeacherActivity.class));
    }


    public void click_search_teacher(View view) {
        TextView textView =(TextView) findViewById(R.id.editText_search_teacher);
        String name =textView.getText().toString();
        database.equalTo(name,adapter,this);
    }
    public void click_search_by_key_teacher(View view)
    {
        CheckBox name=(CheckBox)findViewById(R.id.checkBox_name);
        CheckBox  age=(CheckBox)findViewById(R.id.checkBox_age);
        CheckBox  profession=(CheckBox)findViewById(R.id.checkBox_profession);
        CheckBox address=(CheckBox)findViewById(R.id.checkBox_id);

        String key="";

        if(name.isChecked())
        {
            key="_name";
        }
        if(age.isChecked())
        {
            key="_age";
        }
        if(profession.isChecked())
        {
            key="_profession";
        }
        if(address.isChecked())
        {
            key="_address";
        }
        database.orderByChild(key,adapter,this);

    }

    public void click_imageView_teacher(View view)
    {
        TextView teacher_name_textView=findViewById(R.id.textView_teacher_name);
        TextView teacher_last_name_textView=findViewById(R.id.textView_teacher_last_name);
        TextView teacher_age_textView=findViewById(R.id.textView_teacher_age);
        TextView teacher_phone_textView=findViewById(R.id.textView_teacher_phone);
        TextView  teacher_email_textView=findViewById(R.id.textView_teacher_email);
        TextView teacher_id_textView =findViewById(R.id.textView_teacher_id);
        TextView teacher_profession_textView=findViewById(R.id.textView_teacher_profession);

        String[] message=new String[7];

        message[0] =teacher_name_textView.getText().toString();
        message[1]=teacher_last_name_textView.getText().toString();
        message[2]=teacher_age_textView.getText().toString();
        message[3]=teacher_phone_textView.getText().toString();
        message[4]=teacher_email_textView.getText().toString();
        message[5] =teacher_id_textView.getText().toString();
        message[6]=teacher_profession_textView.getText().toString();

        Intent intent =new Intent(this, UpdateRemoveTeacher.class);
        intent.putExtra(MESSAGE_KEY,message);
        startActivity(intent);

    }

    @Override
    public InterfaceContactList _this() {
        return this;
    }
}