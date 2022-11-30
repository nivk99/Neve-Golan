package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//https://console.firebase.google.com/project/neve-golan/authentication/users
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();


    }

    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser =mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    public void click_register(View view)
    {
        EditText emailEditText=findViewById(R.id.editTextTextEmail);
        EditText passwordEditText=findViewById(R.id.editTextTextPassword);
        mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(),passwordEditText.getText().toString()).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
            public void onComplete(@NonNull Task<AuthResult> task)
        {
            if(task.isSuccessful())
            {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }
            else
            {
                Toast.makeText(LoginActivity.this,"register failed:(",Toast.LENGTH_LONG).show();

            }
        }
    });


    }

    public void click_login(View view)
    {
        EditText emailEditText=findViewById(R.id.editTextTextEmail);
        EditText passwordEditText=findViewById(R.id.editTextTextPassword);
        mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(),passwordEditText.getText().toString()).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }
                else
                {
                    Toast.makeText(LoginActivity.this,"login failed:(",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }


}