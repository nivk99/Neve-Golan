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
import com.example.myapplication.users.User;
import com.example.myapplication.writeUser.AddStudentActivity;
import com.example.myapplication.users.Student;

import java.util.ArrayList;

public class ContactListStudentActivity  extends AppCompatActivity implements InterfaceContactList {
    private  Database database;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_student);
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<User> users_student=new ArrayList<>();
        adapter =new UserAdapter(users_student,new Student());
        recyclerView.setAdapter(adapter);
        database=new Database("users/student");
        database.read_database(adapter,this);
    }



    public void click_add_student(View view) {
        if(LoginActivity.get_admin().is_admin()) {
            startActivity(new Intent(this, AddStudentActivity.class));
        }
        else
        {
            Toast.makeText(this,"Only a manager can",Toast.LENGTH_LONG).show();
        }

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

    @Override
    public InterfaceContactList _this() {
        return this;
    }
}