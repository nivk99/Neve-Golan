package com.example.myapplication.readUser.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication.AddUser.AddStudentActivity;
import com.example.myapplication.R;
import com.example.myapplication.readUser.InterfaceContactList;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.Teacher;
import com.example.myapplication.users.User;

import java.util.ArrayList;

public class ClientContactListTeacherActivity extends AppCompatActivity implements InterfaceContactList {
    private Database database;
    private UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_contact_list_teacher);
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_client_teacher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<User> users_teacher=new ArrayList<>();
        adapter =new UserAdapter(users_teacher,new Teacher());
        recyclerView.setAdapter(adapter);
        database=new Database("users/teacher");
        database.read_database(adapter,this);
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
    public void Click_imageView(View view)
    {

    }
    @Override
    public InterfaceContactList _this() {
        return this;
    }
}