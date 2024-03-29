package com.example.myapplication.login;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.menu.ClientMenuActivity;
import com.example.myapplication.R;
import com.example.myapplication.firebase.Authenticate;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class ClientLoginActivity extends AppCompatActivity implements InterfaceLogin {

    //User definition
     Authenticate _mAuth;

    //Google setting
   GoogleSignInOptions gso;
   GoogleSignInClient gsc;

    //Image Google
   ImageView googleBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login);
    // startActivity(new Intent(ClientLoginActivity.this, ClientMenuActivity.class));
        _mAuth=new Authenticate();
        googleBtn = findViewById(R.id.google_button);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        //Build a GoogleSignInClient with the options specified by gso.
        gsc = GoogleSignIn.getClient(this,gso);

        //Check for existing Google Sign In account, if the user is already signed in
         // the GoogleSignInAccount will be non-null.

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        // if the user is in the database it log-in the
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
    //start intent to sign the user in with the onActivityResult function
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
                Toast.makeText(getApplicationContext(), "משהו השתבש", Toast.LENGTH_SHORT).show();
            }
        }

    }

    void navigateToSecondActivity() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        String personName = acct.getDisplayName();
        String personEmail = acct.getEmail();
        _mAuth.login(personEmail,personName,this);
    }




  //Login to the application
    @Override
    public void login()
    {
        // wait fo the func "onActivityResult" to end and then continue
        finish();
        startActivity(new Intent(ClientLoginActivity.this, ClientMenuActivity.class));
    }

    @Override
    public InterfaceLogin is_this() {
        return this;
    }
}