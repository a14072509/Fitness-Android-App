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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WorkOut extends AppCompatActivity {

    private RelativeLayout aerobicButton;
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
    private Button mAbs;
    private Button mLeg;
    private Button mButt;
    private Button mRightShoulder;
    private Button mLeftShoulder;
    private Button mLeftTricep;
    private Button mRightTricep;
    private Button mChest;
    private Button mRightBicep;
    private Button mLeftBicep;
    private Button mBack;
    private Button mbLeg;
    private Button mbRightShoulder;
    private Button mbLeftShoulder;
    private Button fAbs;
    private Button fLeg;
    private Button fButt;
    private Button fRightShoulder;
    private Button fLeftShoulder;
    private Button fLeftTricep;
    private Button fRightTricep;
    private Button fChest;
    private Button fRightBicep;
    private Button fLeftBicep;
    private Button fBack;
    private Button fbLeg;
    private Button fbRightShoulder;
    private Button fbLeftShoulder;


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

        aerobicButton = (RelativeLayout) findViewById(R.id.aerobic_section);
        aerobicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        mAbs = (Button) findViewById(R.id.male_front_abs);
        mAbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Abs");
            }
        });

        mLeg = (Button) findViewById(R.id.male_front_legs);
        mLeg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Leg");
            }
        });
        mbLeg = (Button) findViewById(R.id.male_back_legs);
        mbLeg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Leg");
            }
        });

        mButt = (Button) findViewById(R.id.male_back_butt);
        mButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Butt and Hips");
            }
        });

        mLeftShoulder = (Button) findViewById(R.id.male_front_shoulder_left);
        mLeftShoulder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Shoulders");
            }
        });

        mRightShoulder = (Button) findViewById(R.id.male_front_shoulder_right);
        mRightShoulder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Shoulders");
            }
        });

        mLeftTricep = (Button) findViewById(R.id.male_back_ticep_left);
        mLeftTricep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Triceps");
            }
        });

        mRightTricep = (Button) findViewById(R.id.male_back_ticep_right);
        mRightTricep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Triceps");
            }
        });

        mChest = (Button) findViewById(R.id.male_front_chest);
        mChest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Chest");
            }
        });

        mLeftBicep = (Button) findViewById(R.id.male_front_bicep_left);
        mLeftBicep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Biceps");
            }
        });

        mRightBicep = (Button) findViewById(R.id.male_front_bicep_right);
        mRightBicep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Biceps");
            }
        });

        mBack = (Button) findViewById(R.id.male_back_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Back");
            }
        });

        mbLeftShoulder = (Button) findViewById(R.id.male_back_shoulder_left);
        mbLeftShoulder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Shoulders");
            }
        });

        mbRightShoulder = (Button) findViewById(R.id.male_back_shoulder_right);
        mbRightShoulder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Shoulders");
            }
        });


        fAbs = (Button) findViewById(R.id.female_front_abs);
        fAbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Abs");
            }
        });

        fLeg = (Button) findViewById(R.id.female_front_legs);
        fLeg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Leg");
            }
        });
        fbLeg = (Button) findViewById(R.id.female_back_legs);
        fbLeg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Leg");
            }
        });

        fButt = (Button) findViewById(R.id.female_back_butt);
        fButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Butt and Hips");
            }
        });

        fLeftShoulder = (Button) findViewById(R.id.female_front_shoulder_left);
        fLeftShoulder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Shoulders");
            }
        });

        fRightShoulder = (Button) findViewById(R.id.female_front_shoulder_right);
        fRightShoulder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Shoulders");
            }
        });

        fLeftTricep = (Button) findViewById(R.id.female_back_ticep_left);
        fLeftTricep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Triceps");
            }
        });

        fRightTricep = (Button) findViewById(R.id.female_back_ticep_right);
        fRightTricep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Triceps");
            }
        });

        fChest = (Button) findViewById(R.id.female_front_chest);
        fChest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Chest");
            }
        });

        fLeftBicep = (Button) findViewById(R.id.female_front_bicep_left);
        fLeftBicep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Biceps");
            }
        });

        fRightBicep = (Button) findViewById(R.id.female_front_bicep_right);
        fRightBicep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Biceps");
            }
        });

        fBack = (Button) findViewById(R.id.female_back_back);
        fBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Back");
            }
        });

        fbLeftShoulder = (Button) findViewById(R.id.female_back_shoulder_left);
        fbLeftShoulder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Shoulders");
            }
        });

        fbRightShoulder = (Button) findViewById(R.id.female_back_shoulder_right);
        fbRightShoulder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Shoulders");
            }
        });

        // todo: get gender information from profile
        db.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //System.out.println(dataSnapshot.child("Gender").getValue());
                //db.child("Users").child(userID).child("Gender").setValue()
                gender = Gender.valueOf(dataSnapshot.child("Gender").getValue().toString());
                updateBodyShadow();
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
        //updateBodyShadow();


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



