package com.example.myapplication.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.AddUser.AddAdminActivity;
import com.example.myapplication.login.InterfaceLogin;
import com.example.myapplication.AddUser.InterfaceAddUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Authenticate extends AppCompatActivity {

    private FirebaseAuth _auth;



    public Authenticate()
    {
        this._auth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth get_auth()
    {
        return _auth;
    }

    public void set_auth(FirebaseAuth _auth)
    {
        this._auth = _auth;
    }

    public void login(String email , String password, InterfaceLogin log)
    {

        this._auth.signInWithEmailAndPassword(email,password).addOnCompleteListener( Authenticate.this,new OnCompleteListener<AuthResult>(){
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    log.login();
                }
                else
                {
                    Toast.makeText((Context) log.is_this(), "login failed:(", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    public void register (String email , String password, InterfaceAddUser add)
    {
        this._auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener((Activity) add._this(),new OnCompleteListener<AuthResult>(){
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText((Context) add._this(),"Added a new user",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText((Context) add._this(),"register failed:(",Toast.LENGTH_LONG).show();
                }



            }
        });

    }

    public void deleteAccount()
    {
        final FirebaseUser currentUser = _auth.getCurrentUser();
        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });


    }

    public void register_admin(String phone , AddAdminActivity add)
    {
        String email_phone=phone+"@gmail.com";

        this._auth.createUserWithEmailAndPassword(email_phone,phone).addOnCompleteListener( add,new OnCompleteListener<AuthResult>(){
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(add,"Added a new admin",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(add,"register failed admin:(",Toast.LENGTH_LONG).show();
                }



            }
        });

    }

}
