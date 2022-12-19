package com.example.myapplication.readUser.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication.userCard.TeacherCardActivity;
import com.example.myapplication.adapter.interfaceSelectListener.InterfaceSelectUserListener;
import com.example.myapplication.R;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.readUser.InterfaceContactList;
import com.example.myapplication.model.FirebaseModelTeacher;
import com.example.myapplication.model.FirebaseModeUser;

import java.io.Serializable;
import java.util.ArrayList;

public class AdminContactListTeacherActivity extends AppCompatActivity implements InterfaceContactList, InterfaceSelectUserListener, Serializable {

    //Database
    private  Database database;

    //Adapter
    private UserAdapter adapter;

    public static final String MESSAGE_KEY="com.example.myapplication.readUser.ContactListTeacherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_contact_list_teacher);
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_admin_teacher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<FirebaseModeUser> users_teacher=new ArrayList<>();
        adapter =new UserAdapter(users_teacher,new FirebaseModelTeacher(),this);
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


    @Override
    public InterfaceContactList _this() {
        return this;
    }

    @Override
    public void onItemClicked(FirebaseModeUser user) {

        FirebaseModelTeacher teacher =(FirebaseModelTeacher)(user);
        String[] message=new String[7];
        message[0] =teacher.get_name();
        message[1]=teacher.get_last_name();
        message[2]=Double.toString(teacher.get_age());
        message[3]=teacher.get_phone();
        message[4]=teacher.get_email();
        message[5] =teacher.get_id();
        message[6]=teacher.get_profession();
        Intent intent =new Intent(this, TeacherCardActivity.class);
        intent.putExtra(MESSAGE_KEY,message);
        startActivity(intent);


    }
}