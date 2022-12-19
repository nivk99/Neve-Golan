package com.example.myapplication.messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



public class EditMessageActivity extends AppCompatActivity {

    Intent data;
    EditText medittitleofnote,meditcontentofnote;
    FloatingActionButton msaveeditnote;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_messagea);
        medittitleofnote=findViewById(R.id.edittitleofnote);
        meditcontentofnote=findViewById(R.id.editcontentofnote);
        msaveeditnote=findViewById(R.id.saveeditnote);

        data=getIntent();

        firebaseFirestore=FirebaseFirestore.getInstance();


        Toolbar toolbar=findViewById(R.id.toolbarofeditnote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        msaveeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newtitle=medittitleofnote.getText().toString();
                String newcontent=meditcontentofnote.getText().toString();

                if(newtitle.isEmpty()||newcontent.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"זה ריק",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    DocumentReference documentReference=firebaseFirestore.collection("messages").document("Admin").collection("myMessages").document(data.getStringExtra("noteId"));
                    Map<String,Object> note=new HashMap<>();
                    note.put("title",newtitle);
                    note.put("content",newcontent);
                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"ההודעה התעדכנה",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditMessageActivity.this, MessageActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"ההודעה לא התעדכנה!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


        String notetitle=data.getStringExtra("title");
        String notecontent=data.getStringExtra("content");
        meditcontentofnote.setText(notecontent);
        medittitleofnote.setText(notetitle);
    }





}