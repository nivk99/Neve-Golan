package com.example.myapplication.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.calendar.CalendarViewActivity;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.readUser.admin.AdminContactListStudentActivity;
import com.example.myapplication.readUser.admin.AdminContactListTeacherActivity;

public class AdminMenuActivity extends AppCompatActivity {
    Authenticate mAuth;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        mAuth = new Authenticate();
        logout = findViewById(R.id.button_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.get_auth().signOut();
                startActivity(new Intent(AdminMenuActivity.this, MainActivity.class));
                finish();
            }
        });}




    public void click_private(View view) {
        Toast.makeText(this, "click_private", Toast.LENGTH_SHORT).show();


    }

    public void click_student(View view) {
        startActivity(new Intent(this, AdminContactListStudentActivity.class));

    }

    public void click_teachers(View view) {
        startActivity(new Intent(this, AdminContactListTeacherActivity.class));
    }

    public void click_timetable(View view) {
        startActivity(new Intent(this, CalendarViewActivity.class));
    }
}