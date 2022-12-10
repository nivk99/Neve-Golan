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
import com.example.myapplication.readUser.InterfaceContactList;
import com.example.myapplication.updateRemoveUser.UpdateRemoveStudent;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.User;
import com.example.myapplication.writeUser.AddStudentActivity;
import com.example.myapplication.users.Student;

import java.util.ArrayList;

public class AdminContactListStudentActivity extends AppCompatActivity implements InterfaceContactList {
    private  Database database;
    private UserAdapter adapter;

    public static final String MESSAGE_KEY="com.example.myapplication.readUser.ContactListStudentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_contact_list_student);
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_admin_student);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<User> users_student=new ArrayList<>();
        adapter =new UserAdapter(users_student,new Student());
        recyclerView.setAdapter(adapter);
        database=new Database("users/student");
        database.read_database(adapter,this);
    }



    public void click_add_student(View view) {
        startActivity(new Intent(this, AddStudentActivity.class));


    }

    public void click_search_student(View view) {
        TextView textView =(TextView) findViewById(R.id.editText_search_student);
        String name =textView.getText().toString();
        database.equalTo(name,adapter,this);
    }
    public void click_search_by_key_student(View view)
    {
        CheckBox name=(CheckBox)findViewById(R.id.checkBox_name);
        CheckBox  age=(CheckBox)findViewById(R.id.checkBox_age);
        CheckBox  clas=(CheckBox)findViewById(R.id.checkBox_class);
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
        if(clas.isChecked())
        {
            key="_class";
        }
        if(address.isChecked())
        {
            key="_address";
        }
        database.orderByChild(key,adapter,this);

    }

    public void Click_imageView(View view)
    {
        TextView student_name_textView=findViewById(R.id.textView_student_name);
        TextView student_last_name_textView=findViewById(R.id.textView_student_last_name);
        TextView student_age_textView=findViewById(R.id.textView_student_age);
        TextView student_phone_textView=findViewById(R.id.textView_student_phone);
        TextView student_email_textView=findViewById(R.id.textView_student_email);
        TextView student_id_textView =findViewById(R.id.textView_student_id);
        TextView student_class_textView=findViewById(R.id.textView_student_class);

        String[] message=new String[7];

        message[0] =student_name_textView.getText().toString();
        message[1]=student_last_name_textView.getText().toString();
        message[2]=student_age_textView.getText().toString();
        message[3]=student_phone_textView.getText().toString();
        message[4]=student_email_textView.getText().toString();
        message[5] =student_id_textView.getText().toString();
        message[6]=student_class_textView.getText().toString();

        Intent intent =new Intent(this, UpdateRemoveStudent.class);
        intent.putExtra(MESSAGE_KEY,message);
        startActivity(intent);
    }

    @Override
    public InterfaceContactList _this() {
        return this;
    }
}