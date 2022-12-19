package com.example.myapplication.readUser.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication.userCard.StudentCardActivity;
import com.example.myapplication.adapter.interfaceSelectListener.InterfaceSelectUserListener;
import com.example.myapplication.R;
import com.example.myapplication.readUser.InterfaceContactList;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.readUser.admin.AdminContactListStudentActivity;
import com.example.myapplication.model.FirebaseModeUser;
import com.example.myapplication.model.FirebaseModelStudent;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientContactListStudentActivity extends AppCompatActivity implements InterfaceContactList, InterfaceSelectUserListener, Serializable {

    //Database
    private Database database;

    //Adapter
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_contact_list_student);
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_client_student);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<FirebaseModeUser> users_student=new ArrayList<>();
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
    public void onItemClicked(FirebaseModeUser user) {
        FirebaseModelStudent student =(FirebaseModelStudent)(user);

        String[] message=new String[8];
        message[0] =student.get_name();
        message[1]=student.get_last_name();
        message[2]=Double.toString(student.get_age());
        message[3]=student.get_phone();
        message[4]=student.get_email();
        message[5] =student.get_id();
        message[6]=student.get_class();
        message[7]="Client";
        Intent intent =new Intent(this, StudentCardActivity.class);
        intent.putExtra(AdminContactListStudentActivity.MESSAGE_KEY,message);
        startActivity(intent);


    }
}
