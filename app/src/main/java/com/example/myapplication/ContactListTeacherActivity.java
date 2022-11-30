package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactListTeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_teacher);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://neve-golan-default-rtdb.firebaseio.com");
        final RecyclerView recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<teacher> users_teacher=new ArrayList<>();
        final TeacherAdapter adapter =new TeacherAdapter(users_teacher);
        recyclerView.setAdapter(adapter);

        Query myquery = database.getReference("users/teacher").orderByChild("_age");
        myquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users_teacher.clear();
                for(DataSnapshot usersnapshot:snapshot.getChildren())
                {
                    Log.d("ContactListActivity","red key"+usersnapshot.getKey());

                    teacher currentuser=usersnapshot.getValue(teacher.class);
                    users_teacher.add(currentuser);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(ContactListTeacherActivity.this,"there are" +users_teacher.size() +" users ",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ContactListTeacherActivity.this,"error onCancelled",Toast.LENGTH_SHORT).show();

            }
        });



    }


    public void click_add_teacher(View view) {
        if(LoginActivity._ADMIN) {
            startActivity(new Intent(this, AddStudentActivity.class));
        }
        else
        {
            Toast.makeText(this,"Only a manager can",Toast.LENGTH_LONG).show();
        }
    }
}