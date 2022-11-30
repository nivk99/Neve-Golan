package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Queue;

public class ContactListStudentActivity extends AppCompatActivity {
    private  FirebaseDatabase database;
    private  Query myquery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_student);
        database = FirebaseDatabase.getInstance("https://neve-golan-default-rtdb.firebaseio.com");
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<student> users_student=new ArrayList<>();
        final StudentAdapter adapter =new StudentAdapter(users_student);
        recyclerView.setAdapter(adapter);

        myquery = database.getReference("users/student").orderByChild("_age");
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
        startActivity(new Intent(this,AddStudentActivity.class));
    }

    public void click_search_student(View view) {
        TextView textView =(TextView) findViewById(R.id.editText_search_student);
        myquery = database.getReference("users/student").orderByChild("_age").equalTo(textView.getText().toString());
    }
}