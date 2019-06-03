package com.example.afs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EquipmentInfo extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Button addExerButton;
    private TextView title;
    private EditText minInput;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private String userID;
    private double height;
    private double weight;
    private int age;
    private Gender gender;

    private VideoView videoView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment_info);

        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.equipmentInfoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addExerButton = (Button)findViewById(R.id.add_button);

        addExerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addExer();
            }
        });

        String instructions = getIntent().getStringExtra("instruction");
        TextView instrText = (TextView)findViewById(R.id.instruction_view);

        instrText.setText(instructions);
        videoView = (VideoView)findViewById(R.id.exer_gif);
        String name = getIntent().getStringExtra("image");
        Resources res = this.getResources();
        int resID = res.getIdentifier(name , "raw", this.getPackageName());
        Uri uri= Uri.parse("android.resource://" + getPackageName() + "/" + resID);

        videoView.setVideoURI(uri);
        videoView.start();

        //Video Loop
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                videoView.start(); //need to make transition seamless.
            }
        });

        videoView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                if (videoView.isPlaying()) {
                    videoView.pause();
                } else {
                    videoView.start();
                }
                return false;
            }
        });

        title = (TextView)findViewById(R.id.equipmentTitle);
        title.setText(getIntent().getStringExtra("name"));
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
    }


    private void addExer()
    {
        try {
            minInput = (EditText)findViewById(R.id.time_input);

            String name = getIntent().getStringExtra("name");


            //TODO Add the calculation of calorie, need database to retrieve user information
            db.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    age = Integer.parseInt(dataSnapshot.child("age").getValue().toString());
                    height = Double.parseDouble(dataSnapshot.child("height").getValue().toString());
                    weight = Integer.parseInt(dataSnapshot.child("weight").getValue().toString());
                    gender = Gender.valueOf(dataSnapshot.child("Gender").getValue().toString());

                    double BMR;
                    if(gender == Gender.FEMALE)
                    {
                        BMR = 655 + 4.3 * weight + 4.7 * 12 * height - 4.7 * age;
                    }
                    else
                    {
                        BMR = 66 + 6.3 * weight + 12.9 * 12 * height - 6.8 * age;
                    }

                    double met = getIntent().getDoubleExtra("met", 0);
                    double min = Double.parseDouble(minInput.getText().toString());
                    weight /= 2.2;
                    double calorieBurnt = weight * met * min/60;
                    //TODO

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            finish();
        }
        catch(Exception e) {
            System.out.println(e);
            return;
        }

    }

}
