package com.example.myapplication.readUser.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication.adapter.InterfaceSelectListener;
import com.example.myapplication.R;
import com.example.myapplication.readUser.InterfaceContactList;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.users.User;
import com.example.myapplication.users.FirebaseModelStudent;

import java.util.ArrayList;

public class ClientContactListStudentActivity extends AppCompatActivity implements InterfaceContactList, InterfaceSelectListener {

    private Database database;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_contact_list_student);
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_client_student);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<User> users_student=new ArrayList<>();
        adapter =new UserAdapter(users_student,new FirebaseModelStudent(),this);
        recyclerView.setAdapter(adapter);
        database=new Database("users/student");
        database.read_database(adapter,this);
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




    public InterfaceContactList _this() {
        return this;
    }


    @Override
    public void onItemClicked(User user) {


    }
}
