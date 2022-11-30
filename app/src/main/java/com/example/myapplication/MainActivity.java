package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click_private(View view)
    {
        Toast.makeText(this,"click_private",Toast.LENGTH_SHORT).show();


    }

    public void click_student(View view)
    {
        startActivity(new Intent(this,ContactListStudentActivity.class));

    }
    public void click_teachers(View view)
    {
        startActivity(new Intent(this,ContactListTeacherActivity.class));

    }
    public void click_timetable(View view)
    {
        Toast.makeText(this,"click_timetable",Toast.LENGTH_SHORT).show();

    }
    public void click_logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}