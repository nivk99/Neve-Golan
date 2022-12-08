package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.users.Admin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //Admin
    static Admin admin;
     Authenticate _mAuth;


    //google
   GoogleSignInOptions gso;
   GoogleSignInClient gsc;
   ImageView googleBtn;

    //EditText
    private EditText editText_email,editText_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _mAuth=new Authenticate();
        admin=new Admin();
        editText_email =findViewById(R.id.username);
        editText_password =findViewById(R.id.password);
        googleBtn = findViewById(R.id.google_button);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        //Build a GoogleSignInClient with the options specified by gso.
        gsc = GoogleSignIn.getClient(this,gso);

         //Check for existing Google Sign In account, if the user is already signed in
         // the GoogleSignInAccount will be non-null.

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


    }

    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        String personName = acct.getDisplayName();
        String personEmail = acct.getEmail();
        _mAuth.login(personEmail,personName,this);
    }

    public void check()
    {
        finish();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }



    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser =_mAuth.get_auth().getCurrentUser();
        if(currentUser!=null)
        {
            startActivity(new Intent(this, MainActivity.class));
        }
    }


    public void click_login(View view)
    {
        String email=editText_email.getText().toString();
        String password=editText_password.getText().toString();

        if(email.equals(admin.get_name())&&password.equals(admin.get_password()))
        {
            admin.set_is_admin(true);
            startActivity(new Intent(this, MainActivity.class));
        }
        else
        {
            if(!email.contains("gmail.com"))
            {
                _mAuth.login(email,password,this);
            }
            else
            {
                Toast.makeText(this,"login failed:(",Toast.LENGTH_LONG).show();
            }

       }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public static Admin get_admin()
    {
        return admin;
    }




}