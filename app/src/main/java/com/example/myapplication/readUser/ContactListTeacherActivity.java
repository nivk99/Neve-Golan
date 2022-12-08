package com.example.myapplication.readUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.Student;
import com.example.myapplication.users.User;
import com.example.myapplication.writeUser.AddTeacherActivity;

import java.util.ArrayList;

public class ContactListTeacherActivity extends AppCompatActivity implements InterfaceContactList {

    private  Database database;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_teacher);
        final RecyclerView recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<User> users_student=new ArrayList<>();
        adapter =new UserAdapter(users_student,new Student());
        recyclerView.setAdapter(adapter);
        database=new Database("users/teacher");
        database.read_database(adapter,this);

    }


    public void click_add_teacher(View view)
    {
        if(LoginActivity.get_admin().is_admin()) {
            startActivity(new Intent(this, AddTeacherActivity.class));
        }
        else
        {
            Toast.makeText(this,"Only a manager can",Toast.LENGTH_LONG).show();
        }
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
        CheckBox address=(CheckBox)findViewById(R.id.checkBox_Address);

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

    @Override
    public InterfaceContactList _this() {
        return this;
    }
}