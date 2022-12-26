package com.example.myapplication.userCard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.menu.AdminMenuActivity;
import com.example.myapplication.readUser.admin.AdminContactListTeacherActivity;
import com.example.myapplication.notification.sms_user_Activity;
import com.example.myapplication.userUpdate.UpdateTeacher;
import com.google.android.material.navigation.NavigationView;

public class TeacherCardActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY="com.example.myapplication.userCard.TeacherCard";


    //Teacher definition
    private TextView teacher_name_textView;
    private TextView teacher_last_name_textView;
    private TextView teacher_age_textView;
    private TextView teacher_phone_textView;
    private TextView teacher_email_textView;
    private TextView teacher_id_textView;
    private TextView teacher_profession_textView;

    //key
    private String id;

    //Database
    private Database database;

    private  String[] message;

    //Setting menu view
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_card);

        update_values();

        drawerLayout=findViewById(R.id.teacher_card_screen);
        navigationView=findViewById(R.id.navigationView_teacher);
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
                        startActivity(new Intent(TeacherCardActivity.this, AdminMenuActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_update:
                        update();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_sms:
                        click_sms();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_clear:
                        remove_teacher();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return true;
            }
        });
    }



    public void update_values()
    {
        database=new Database("users/teacher");

        teacher_name_textView=(TextView)findViewById(R.id.editText_teacher_name);
        teacher_last_name_textView=(TextView)findViewById(R.id.editText_teacher_last_name);
        teacher_age_textView=(TextView)findViewById(R.id.editText_teacher_age);
        teacher_phone_textView=(TextView)findViewById(R.id.editText_teacher_phone);
        teacher_email_textView=(TextView)findViewById(R.id.editText_teacher_email);
        teacher_id_textView=(TextView)findViewById(R.id.editText_teacher_id);
        teacher_profession_textView=(TextView)findViewById(R.id.editText_teacher_profession);
        Intent intent=getIntent();
        message=intent.getStringArrayExtra(AdminContactListTeacherActivity.MESSAGE_KEY);
        teacher_name_textView.setText(message[0]);
        teacher_last_name_textView.setText(message[1]);
        teacher_age_textView.setText(message[2]);
        teacher_phone_textView.setText(message[3]);
        teacher_email_textView.setText(message[4]);
        teacher_id_textView.setText(message[5]);
        teacher_profession_textView.setText(message[6]);
        id=message[5];
    }

    public void click_sms()
    {
        Intent intent =new Intent(this, sms_user_Activity.class);
        intent.putExtra("_phone",teacher_phone_textView.getText().toString());
        startActivity(intent);

    }

    public void remove_teacher()
    {

        database.remove(this.id);

    }

    public void update()
    {
        Intent intent =new Intent(this, UpdateTeacher.class);
        intent.putExtra(MESSAGE_KEY,message);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item) ;
    }
}