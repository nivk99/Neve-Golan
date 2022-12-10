package com.example.myapplication.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.AddUser.AddAdminActivity;
import com.example.myapplication.AddUser.AddClientActivity;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.add_admin:
                startActivity(new Intent(this, AddAdminActivity.class));
                return true;
            case R.id.add_user:
                startActivity(new Intent(this, AddClientActivity.class));
                return true;
            case R.id.delete_admin:
                Toast.makeText(this, "delete_admin", Toast.LENGTH_SHORT).show();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


}