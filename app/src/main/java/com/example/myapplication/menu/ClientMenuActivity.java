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
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.myapplication.AddUser.AddStudentActivity;
import com.example.myapplication.R;
import com.example.myapplication.calendar.CalendarViewActivity;
import com.example.myapplication.login.ClientLoginActivity;
import com.example.myapplication.messages.messageClientActivity;
import com.example.myapplication.notes.notesactivity;
import com.example.myapplication.personalInformation.Personal_Information_teacher;
import com.example.myapplication.readUser.client.ClientContactListStudentActivity;
import com.example.myapplication.readUser.client.ClientContactListTeacherActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ClientMenuActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;
    Button signOutBtn;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item) ;
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_menu);

        drawerLayout=findViewById(R.id.main_screen_client);
        navigationView=findViewById(R.id.navigationView);
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
                        startActivity(new Intent(ClientMenuActivity.this, ClientMenuActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_private:
                        startActivity(new Intent(ClientMenuActivity.this, Personal_Information_teacher.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_calendar:
                        startActivity(new Intent(ClientMenuActivity.this, CalendarViewActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_messages:
                        startActivity(new Intent(ClientMenuActivity.this, messageClientActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_feedbacks:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_requests:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_notes:

                        startActivity(new Intent(ClientMenuActivity.this, notesactivity.class));
                         drawerLayout.closeDrawer(GravityCompat.START);

                        break;

                    case R.id.nav_student:
                        startActivity(new Intent(ClientMenuActivity.this, ClientContactListStudentActivity.class));

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_teacher:
                        startActivity(new Intent(ClientMenuActivity.this, ClientContactListTeacherActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_add_student_client:
                        startActivity(new Intent(ClientMenuActivity.this, AddStudentActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_logout:
                        signOut();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return true;
            }
        });




        name = findViewById(R.id.textView_name_client);
        email =null;

        signOutBtn = findViewById(R.id.button_logout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null)
        {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
//            email.setText(personEmail);
        }
        else
        {
            name.setText("??");
//            email.setText("??");
        }

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


    }


    void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(ClientMenuActivity.this, ClientLoginActivity.class));
            }
        });
    }


    public void client_click_private(View view) {
        startActivity(new Intent(this, Personal_Information_teacher.class));

    }

    public void client_click_student(View view) {
        startActivity(new Intent(this, ClientContactListStudentActivity.class));

    }

    public void client_click_teachers(View view) {
        startActivity(new Intent(this, ClientContactListTeacherActivity.class));
    }

    public void client_click_timetable(View view) {
        startActivity(new Intent(this, CalendarViewActivity.class));
    }
}