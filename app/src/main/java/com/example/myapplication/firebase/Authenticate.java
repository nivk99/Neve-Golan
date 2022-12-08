package com.example.myapplication.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.widget.Toast;

import com.example.myapplication.writeUser.AddTeacherActivity;
import com.example.myapplication.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


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

    public void login(String email , String password,LoginActivity log)
    {

        this._auth.signInWithEmailAndPassword(email,password).addOnCompleteListener( Authenticate.this,new OnCompleteListener<AuthResult>(){
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                   log.check();
                }
                else
                {
                    Toast.makeText(log, "login failed:(", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    public void register (String email , String password, AddTeacherActivity add)
    {
        this._auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( add,new OnCompleteListener<AuthResult>(){
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(add,"Added a new user",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(add,"register failed:(",Toast.LENGTH_LONG).show();
                }



            }
        });

    }

}
