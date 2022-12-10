package com.example.myapplication.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.calendar.CalendarViewActivity;
import com.example.myapplication.login.ClientLoginActivity;
import com.example.myapplication.readUser.client.ClientContactListStudentActivity;
import com.example.myapplication.readUser.client.ClientContactListTeacherActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ClientMenuActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;
    Button signOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_menu);
        name = findViewById(R.id.textView_Name);
        email = findViewById(R.id.textView_email_);

        signOutBtn = findViewById(R.id.button_logout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null)
        {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }
        else
        {
            name.setText("??");
            email.setText("??");
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
        Toast.makeText(this, "click_private", Toast.LENGTH_SHORT).show();


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