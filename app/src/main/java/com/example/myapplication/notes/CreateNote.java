package com.example.myapplication.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateNote extends AppCompatActivity {


    //create title of note
    //create content of note
    EditText mcreatetitleofnote,mcreatecontentofnote;

    //Save note button
    FloatingActionButton msavenote;


    //User
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    //firebase store
    FirebaseFirestore firebaseFirestore;

    //progressbar to create note
    ProgressBar mprogressbarofcreatenote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        //Save note button
        msavenote=findViewById(R.id.savenote);

        //create content of note
        mcreatecontentofnote=findViewById(R.id.createcontentofnote);

        //create title of note
        mcreatetitleofnote=findViewById(R.id.createtitleofnote);

        //progressbar to create note
        mprogressbarofcreatenote=findViewById(R.id.progressbarofcreatenote);

        Toolbar toolbar=findViewById(R.id.toolbarofcreatenote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //firebase User
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        //firebase store
        firebaseFirestore=FirebaseFirestore.getInstance();

        //If I clicked the save button
        msavenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                String title=mcreatetitleofnote.getText().toString().trim();
                String content=mcreatecontentofnote.getText().toString().trim();

                //if empty
                if(title.isEmpty() || content.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"שני השדות נדרשים",Toast.LENGTH_SHORT).show();
                }
                else
                {

                    mprogressbarofcreatenote.setVisibility(View.VISIBLE);

                    DocumentReference documentReference=firebaseFirestore.collection("notes").document(firebaseUser.getUid()).collection("myNotes").document();
                    Map<String ,Object> note= new HashMap<>();
                    note.put("title",title);
                    note.put("content",content);

                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"הפתק נוצרה בהצלחה",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateNote.this, NotesActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"הפתק לא נוצר ",Toast.LENGTH_SHORT).show();
                            mprogressbarofcreatenote.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });


    }


   //speak and write
    public void click_speak(View view)
    {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(intent ,10);
        }
        else
        {
            Toast.makeText(this,"המכשיר שלך לא תומך בזה",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case 10:
                if(resultCode==RESULT_OK && data!=null)
                {
                    ArrayList<String > result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mcreatecontentofnote.setText(result.get(0));
                }
                break;
        }
    }

}