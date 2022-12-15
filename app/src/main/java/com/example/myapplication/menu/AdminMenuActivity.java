package com.example.myapplication.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.myapplication.AddUser.AddAdminActivity;
import com.example.myapplication.AddUser.AddClientActivity;
import com.example.myapplication.AddUser.AddStudentActivity;
import com.example.myapplication.AddUser.AddTeacherActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.calendar.CalendarViewActivity;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.messages.MessageActivity;
import com.example.myapplication.notes.notesactivity;
import com.example.myapplication.personalInformation.Personal_Information_teacher;
import com.example.myapplication.readUser.admin.AdminContactListStudentActivity;
import com.example.myapplication.readUser.admin.AdminContactListTeacherActivity;
import com.google.android.material.navigation.NavigationView;

public class AdminMenuActivity extends AppCompatActivity {
    Authenticate mAuth;
    Button logout;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        mAuth = new Authenticate();
        logout = findViewById(R.id.button_logout);
        drawerLayout=findViewById(R.id.main_screen_admin);
        navigationView=findViewById(R.id.navigationView_admin);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        startActivity(new Intent(AdminMenuActivity.this, ClientMenuActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_private:
                        startActivity(new Intent(AdminMenuActivity.this, Personal_Information_teacher.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_calendar:
                        startActivity(new Intent(AdminMenuActivity.this, CalendarViewActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_messages:
                        startActivity(new Intent(AdminMenuActivity.this, MessageActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_feedbacks:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_requests:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_notes:
                        startActivity(new Intent(AdminMenuActivity.this, notesactivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;



                    case R.id.nav_student:
                        startActivity(new Intent(AdminMenuActivity.this, AdminContactListStudentActivity.class));

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_teacher:
                        startActivity(new Intent(AdminMenuActivity.this, AdminContactListTeacherActivity.class));


                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_logout:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_add_teacher:
                        startActivity(new Intent(AdminMenuActivity.this, AddTeacherActivity.class));                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.nav_add_student:
                        startActivity(new Intent(AdminMenuActivity.this, AddStudentActivity.class));                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.add_admin:
                        startActivity(new Intent(AdminMenuActivity.this, AddAdminActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.add_user:
                        startActivity(new Intent(AdminMenuActivity.this, AddClientActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.delete_admin:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;

                }
                return true;
            }
        });






        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.get_auth().signOut();
                startActivity(new Intent(AdminMenuActivity.this, MainActivity.class));
                finish();
            }
        });}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item) ;
    }





}