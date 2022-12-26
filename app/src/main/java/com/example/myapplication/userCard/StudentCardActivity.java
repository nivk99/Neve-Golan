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

import com.example.myapplication.feedback.FeedbackActivity;
import com.example.myapplication.R;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.menu.AdminMenuActivity;
import com.example.myapplication.menu.ClientMenuActivity;
import com.example.myapplication.readUser.admin.AdminContactListStudentActivity;
import com.example.myapplication.notification.sms_user_Activity;
import com.example.myapplication.userUpdate.UpdateStudent;
import com.google.android.material.navigation.NavigationView;

public class StudentCardActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY="com.example.myapplication.userCard.StudentCard";


    //Setting menu view
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    //student definition
    private TextView student_name_textView;
    private TextView student_last_name_textView;
    private TextView student_age_textView;
    private TextView student_phone_textView;
    private TextView student_email_textView;
    private TextView student_id_textView;
    private TextView student_class_textView;

    private  String id;

    private Database database;

    private  String[] message;

    private boolean is_admin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_card);
        update_values();
        drawerLayout=findViewById(R.id.student_card_screen);
        navigationView=findViewById(R.id.navigationView_student);
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
                        if(is_admin)
                            startActivity(new Intent(StudentCardActivity.this, AdminMenuActivity.class));
                        else
                            startActivity(new Intent(StudentCardActivity.this, ClientMenuActivity.class));

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_update:
                        update();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_feedbacks:
                        feedback();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_sms:
                        click_sms();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_clear:
                        remove_student();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item) ;
    }



    private  void update_values()
    {
        database=new Database("users/student");

        student_name_textView=(TextView)findViewById(R.id.editText_student_name);
        student_last_name_textView=(TextView)findViewById(R.id.editText_student_last_name);
        student_age_textView=(TextView)findViewById(R.id.editText_student_age);
        student_phone_textView=(TextView)findViewById(R.id.editText_student_phone);
        student_email_textView=(TextView)findViewById(R.id.editText_student_email);
        student_id_textView=(TextView)findViewById(R.id.editText_student_id);
        student_class_textView=(TextView)findViewById(R.id.editText_student_class);


        Intent intent=getIntent();
        message=intent.getStringArrayExtra(AdminContactListStudentActivity.MESSAGE_KEY);
        student_name_textView.setText(message[0]);
        student_last_name_textView.setText(message[1]);
        student_age_textView.setText(message[2]);
        student_phone_textView.setText(message[3]);
        student_email_textView.setText(message[4]);
        student_id_textView.setText(message[5]);
        student_class_textView.setText(message[6]);
        id=message[5];

        is_admin= message[7].equals("Admin");
    }

    public void click_sms()
    {
        Intent intent =new Intent(this, sms_user_Activity.class);
        intent.putExtra("_phone",student_phone_textView.getText().toString());
        startActivity(intent);

    }

    public void remove_student()
    {

        database.remove(this.id);

    }

    public void update()
    {
        Intent intent =new Intent(this, UpdateStudent.class);
        intent.putExtra(MESSAGE_KEY,message);
        startActivity(intent);

    }

    public void feedback()
    {
        Intent intent =new Intent(this, FeedbackActivity.class);
        intent.putExtra("_id",id);
        startActivity(intent);
    }
}