package com.example.myapplication.readUser.client;

import static com.example.myapplication.readUser.model_teacher_Activity.find_the_sortType_teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.interfaceSelectListener.InterfaceSelectUserListener;
import com.example.myapplication.R;
import com.example.myapplication.readUser.InterfaceContactList;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.model.FirebaseModelTeacher;
import com.example.myapplication.model.FirebaseModeUser;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientContactListTeacherActivity extends AppCompatActivity implements InterfaceContactList, InterfaceSelectUserListener, Serializable {

    //Database
    private Database database;

    //Adapter
    private UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_contact_list_teacher);
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_client_teacher);
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

        String key= find_the_sortType_teacher(name, age, profession, address);
        if(key != null)
            database.orderByChild(key,adapter,this);

    }
    @Override
    public InterfaceContactList _this() {
        return this;
    }

    @Override
    public void onItemClicked(FirebaseModeUser user) {
        Toast.makeText(this, "delete_admin", Toast.LENGTH_SHORT).show();

    }
}