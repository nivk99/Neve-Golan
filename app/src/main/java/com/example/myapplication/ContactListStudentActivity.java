package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactListStudentActivity extends AppCompatActivity {
    private  FirebaseDatabase database;
    private  Query myquery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_student);

        database = FirebaseDatabase.getInstance("https://neve-golan-default-rtdb.firebaseio.com/");
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<student> users_student=new ArrayList<>();
        final StudentAdapter adapter =new StudentAdapter(users_student);
        recyclerView.setAdapter(adapter);

        myquery = database.getReference("users/student");
        myquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users_student.clear();
                for(DataSnapshot usersnapshot:snapshot.getChildren())
                {
                    Log.d("ContactListActivity","red key"+usersnapshot.getKey());

                    student currentuser=usersnapshot.getValue(student.class);
                    users_student.add(currentuser);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(ContactListStudentActivity.this,"there are" +users_student.size() +" users ",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ContactListStudentActivity.this,"error onCancelled",Toast.LENGTH_SHORT).show();

            }
        });



    }


    public void orderByChild(String key)
    {
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<student> users_student=new ArrayList<>();
        final StudentAdapter adapter =new StudentAdapter(users_student);
        recyclerView.setAdapter(adapter);

        myquery = database.getReference("users/student").orderByChild(key);
        myquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users_student.clear();
                for(DataSnapshot usersnapshot:snapshot.getChildren())
                {
                    Log.d("ContactListActivity","red key"+usersnapshot.getKey());

                    student currentuser=usersnapshot.getValue(student.class);
                    users_student.add(currentuser);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(ContactListStudentActivity.this,"there are" +users_student.size() +" users ",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ContactListStudentActivity.this,"error onCancelled",Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void equalTo(String name)
    {
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<student> users_student=new ArrayList<>();
        final StudentAdapter adapter =new StudentAdapter(users_student);
        recyclerView.setAdapter(adapter);

        myquery = database.getReference("users/student").orderByChild("_name").equalTo(name);
        myquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users_student.clear();
                for(DataSnapshot usersnapshot:snapshot.getChildren())
                {
                    Log.d("ContactListActivity","red key"+usersnapshot.getKey());

                    student currentuser=usersnapshot.getValue(student.class);
                    users_student.add(currentuser);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(ContactListStudentActivity.this,"there are" +users_student.size() +" users ",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ContactListStudentActivity.this,"error onCancelled",Toast.LENGTH_SHORT).show();

            }
        });

    }



    public void click_add_student(View view) {
        if(LoginActivity._ADMIN) {
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
        equalTo(name);
    }
    public void click_search_by_key_student(View view)
    {
        CheckBox name=(CheckBox)findViewById(R.id.checkBox_name);
        CheckBox  age=(CheckBox)findViewById(R.id.checkBox_age);
        CheckBox  clas=(CheckBox)findViewById(R.id.checkBox_class);
        CheckBox address=(CheckBox)findViewById(R.id.checkBox_Address);

        if(name.isChecked())
        {
            orderByChild("_name");
        }
        if(age.isChecked())
        {
            orderByChild("_age");
        }
        if(clas.isChecked())
        {
            orderByChild("_class");
        }
        if(address.isChecked())
        {
            orderByChild("_address");
        }

    }
}