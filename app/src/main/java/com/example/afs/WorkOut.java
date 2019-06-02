package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WorkOut extends AppCompatActivity {

    private Button aerobicButton;
    private ImageButton flipButton;
    private RelativeLayout maleFront;
    private RelativeLayout maleBack;
    private RelativeLayout femaleFront;
    private RelativeLayout femaleBack;
    private Gender gender = Gender.MALE;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private String userID;
    private boolean front;
    private Button absButton;
    private Button legButton;
    private Button buttButton;
    private Button rightShoulderButton;
    private Button leftShoulderButton;
    private Button leftTricepButton;
    private Button rightTricepButton;
    private Button blegButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);

        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();

        aerobicButton = (Button) findViewById(R.id.aerobic);
        aerobicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Leg");
            }
        });
        absButton = (Button) findViewById(R.id.abs);
        absButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Abs");
            }
        });

        legButton = (Button) findViewById(R.id.legs);
        legButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Leg");
            }
        });
        blegButton = (Button) findViewById(R.id.b_legs);
        blegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Leg");
            }
        });

        buttButton = (Button) findViewById(R.id.butt);
        buttButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Butt and Hips");
            }
        });

        leftShoulderButton = (Button) findViewById(R.id.shoulder_left);
        leftShoulderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Shoulders");
            }
        });

        rightShoulderButton = (Button) findViewById(R.id.shoulder_right);
        rightShoulderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Shoulders");
            }
        });

        leftTricepButton = (Button) findViewById(R.id.ticep_left);
        leftTricepButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Triceps");
            }
        });

        rightTricepButton = (Button) findViewById(R.id.ticep_right);
        rightTricepButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Triceps");
            }
        });
        // todo: get gender information from profile
        db.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //System.out.println(dataSnapshot.child("Gender").getValue());
                gender = Gender.valueOf(dataSnapshot.child("Gender").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        flipButton = (ImageButton) findViewById(R.id.flip_button);
        maleFront = (RelativeLayout) findViewById(R.id.male_front_section);
        maleBack = (RelativeLayout) findViewById(R.id.male_back_section);
        femaleFront = (RelativeLayout) findViewById(R.id.female_front_section);
        femaleBack = (RelativeLayout) findViewById(R.id.female_back_section);
        front = true;
        updateBodyShadow();


        flipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateFrontBack();
                updateBodyShadow();
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_workout:
                        Intent intent1 = new Intent(WorkOut.this, WorkOut.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_dietplan:
                        Intent intent2 = new Intent(WorkOut.this, DietPlan.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_calendar:
                        Intent intent3 = new Intent(WorkOut.this, Calendar.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(WorkOut.this, Profile.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void enterExerciseList(String body) {
        Intent intent = new Intent(this, ExerciseList.class);
        System.out.println(body);
        intent.putExtra("body", body);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void updateFrontBack() { front = !front; }

    private void updateBodyShadow()
    {
        maleFront.setVisibility(View.INVISIBLE);
        femaleFront.setVisibility(View.INVISIBLE);
        maleBack.setVisibility(View.INVISIBLE);
        femaleBack.setVisibility(View.INVISIBLE);

        if(gender != Gender.FEMALE)
        {
            if(front)
                maleFront.setVisibility(View.VISIBLE);
            else
                maleBack.setVisibility(View.VISIBLE);
        }
        else
        {
            if(front)
                femaleFront.setVisibility(View.VISIBLE);
            else
                femaleBack.setVisibility(View.VISIBLE);
        }
    }
}



