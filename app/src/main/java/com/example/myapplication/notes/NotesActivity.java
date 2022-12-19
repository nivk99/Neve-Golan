package com.example.myapplication.notes;

import androidx.appcompat.app.AppCompatActivity;
import static java.time.LocalDateTime.now;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.FirebaseModelNote;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesActivity extends AppCompatActivity {


    //create notes
    FloatingActionButton mcreatenotesfab;

    //firebase Auth
    private FirebaseAuth firebaseAuth;

    //recycler view
    RecyclerView mrecyclerview;

    //staggered Grid Layout Manager
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    //firebase store
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    //note Adapter
    FirestoreRecyclerAdapter<FirebaseModelNote,NoteViewHolder> noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notesactivity);

        mcreatenotesfab=findViewById(R.id.createnotefab);
        firebaseAuth=FirebaseAuth.getInstance();

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore=FirebaseFirestore.getInstance();

        //create notes
        mcreatenotesfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(NotesActivity.this, CreateNote.class));

            }
        });

       //Reading from the database
        Query query=firebaseFirestore.collection("notes").document(firebaseUser.getUid()).collection("myNotes").orderBy("title",Query.Direction.ASCENDING);

        //pull the information from the database
        FirestoreRecyclerOptions<FirebaseModelNote> allusernotes= new FirestoreRecyclerOptions.Builder<FirebaseModelNote>().setQuery(query,FirebaseModelNote.class).build();

        //request Permissions
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        //Adapter
        noteAdapter= new FirestoreRecyclerAdapter<FirebaseModelNote, NoteViewHolder>(allusernotes) {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i, @NonNull FirebaseModelNote firebasemodel) {



                ImageView popupbutton=noteViewHolder.itemView.findViewById(R.id.menupopbutton);

                //Color the note
                int colourcode=getRandomColor();

                //set Background Color
                noteViewHolder.mnote.setBackgroundColor(noteViewHolder.itemView.getResources().getColor(colourcode,null));

                //set Text
                noteViewHolder.notetitle.setText(firebasemodel.getTitle());
                noteViewHolder.notecontent.setText(firebasemodel.getContent());
                String docId=noteAdapter.getSnapshots().getSnapshot(i).getId();


                //Note menu - edit
                popupbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PopupMenu popupMenu=new PopupMenu(v.getContext(),v);
                        popupMenu.setGravity(Gravity.END);
                        popupMenu.getMenu().add("לערוך").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Intent intent=new Intent(v.getContext(), EditNoteActivity.class);
                                intent.putExtra("title",firebasemodel.getTitle());
                                intent.putExtra("content",firebasemodel.getContent());
                                intent.putExtra("noteId",docId);
                                v.getContext().startActivity(intent);
                                return false;
                            }
                        });

                        //Note menu - Clear
                        popupMenu.getMenu().add("למחוק").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.this);
                                builder.setTitle("Delete");
                                builder.setMessage("אתה בטוח שאתה רוצה למחוק?");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        DocumentReference documentReference=firebaseFirestore.collection("notes").document(firebaseUser.getUid()).collection("myNotes").document(docId);
                                        documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(v.getContext(),"הערה זו נמחקה",Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(v.getContext(),"הערה זו לא נמחקת",Toast.LENGTH_SHORT).show();
                                            }

                                        });
                                    }
                                });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder.create().show();
                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });


            }
            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_layout,parent,false);
                return new NoteViewHolder(view);
            }
        };

        mrecyclerview=findViewById(R.id.recyclerview);
        mrecyclerview.setHasFixedSize(true);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mrecyclerview.setLayoutManager(staggeredGridLayoutManager);
        mrecyclerview.setAdapter(noteAdapter);

    }

    public class NoteViewHolder extends RecyclerView.ViewHolder
    {
        private TextView notetitle;
        private TextView notecontent;
        LinearLayout mnote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            notetitle=itemView.findViewById(R.id.notetitle);
            notecontent=itemView.findViewById(R.id.notecontent);
            mnote=itemView.findViewById(R.id.note);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.translate_anim);
            mnote.setAnimation(translate_anim);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(noteAdapter!=null)
        {
            noteAdapter.stopListening();
        }
    }


    private int getRandomColor()
    {
        List<Integer> colorcode=new ArrayList<>();
        colorcode.add(R.color.gray);
        colorcode.add(R.color.pink);
        colorcode.add(R.color.lightgreen);
        colorcode.add(R.color.skyblue);
        colorcode.add(R.color.color1);
        colorcode.add(R.color.color2);
        colorcode.add(R.color.color3);

        colorcode.add(R.color.color4);
        colorcode.add(R.color.color5);
        colorcode.add(R.color.green);

        Random random=new Random();
        int number=random.nextInt(colorcode.size());
        return colorcode.get(number);
    }


}