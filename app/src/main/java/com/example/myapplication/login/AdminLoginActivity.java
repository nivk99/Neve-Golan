package com.example.myapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.menu.AdminMenuActivity;
import com.example.myapplication.R;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.menu.ClientMenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;





public class AdminLoginActivity extends AppCompatActivity implements InterfaceLogin {

    EditText phone, otp;
    Button btngenOTP, btnverify;
    Authenticate mAuth;
    String verificationID;
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
      startActivity(new Intent(AdminLoginActivity.this, AdminMenuActivity.class));
        phone = findViewById(R.id.username);
        otp = findViewById(R.id.password);
        btngenOTP = findViewById(R.id.click_login);
        btnverify =findViewById(R.id.btnverifyOTP);
        mAuth = new Authenticate();
        bar = findViewById(R.id.bar);
        btngenOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(TextUtils.isEmpty(phone.getText().toString()))
                {
                    Toast.makeText(AdminLoginActivity.this, "Enter Valid Phone No.", Toast.LENGTH_SHORT).show();
                }
                else {
                    String google="@gmail.com";
                    String number = phone.getText().toString();
                    mAuth.login(number+google,number,AdminLoginActivity.this);

                }
            }
        });
    }
    @Override
    public void login()
    {
        String number = phone.getText().toString();
        bar.setVisibility(View.VISIBLE);
        sendverificationcode(number);

        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(TextUtils.isEmpty(otp.getText().toString()))
                {
                    Toast.makeText(AdminLoginActivity.this, "Wrong OTP Entered", Toast.LENGTH_SHORT).show();
                }
                else
                    verifycode(otp.getText().toString());
            }
        });

    }

    private void sendverificationcode(String phoneNumber)
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth.get_auth())
                        .setPhoneNumber("+972"+phoneNumber)  // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential)
        {
            final String code = credential.getSmsCode();
            if(code!=null)
            {
                verifycode(code);
            }
        }
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(AdminLoginActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token)
        {
            super.onCodeSent(s, token);
            verificationID = s;
            Toast.makeText(AdminLoginActivity.this, "Code sent", Toast.LENGTH_SHORT).show();
            btnverify.setEnabled(true);
            bar.setVisibility(View.INVISIBLE);
        }};
    private void verifycode(String Code)
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID,Code);
        signinbyCredentials(credential);
    }

    private void signinbyCredentials(PhoneAuthCredential credential)
    {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(AdminLoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AdminLoginActivity.this, AdminMenuActivity.class));
                        }

                    }
                });}

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null)
        {
//            startActivity(new Intent(AdminLoginActivity.this, AdminMenuActivity.class));
//            finish();
        }}


    @Override
    public InterfaceLogin is_this() {
        return this;
    }
}















