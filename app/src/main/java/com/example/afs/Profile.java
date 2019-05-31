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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private Button logOutButton;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private ImageButton editButton;
    private TextView ageText;
    private TextView heightText;
    private TextView weightText;
    private TextView usernameText;
    private String userID;
    private String newUserName;
    private String newAge;
    private String newHeight;
    private String newWeight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        db = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();

        usernameText = (TextView) findViewById(R.id.Username);
        ageText = (TextView) findViewById(R.id.edit_age);
        heightText = (TextView) findViewById(R.id.edit_height);
        weightText = (TextView) findViewById(R.id.edit_weight);


        db.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newUserName = (String)dataSnapshot.child("userName").getValue();
                System.out.println("Sava Method: \n" + dataSnapshot.getValue());
                System.out.println("Sava Method asdfasdf: \n" + dataSnapshot.child("userName").getValue());
                newAge = dataSnapshot.child("age").getValue().toString();
                newHeight = dataSnapshot.child("height").getValue().toString();
                newWeight = dataSnapshot.child("weight").getValue().toString();

                System.out.println("AAABBBCCC\n" + newUserName);
                usernameText.setText(newUserName);
                ageText.setText(newAge);
                heightText.setText(newHeight);
                weightText.setText(newWeight);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        logOutButton = (Button) findViewById(R.id.logout_button);
        logOutButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Profile.this, login.class);
                startActivity(intent);
            }
        });

        editButton = (ImageButton) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterEditProfile();
            }
        });



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_workout:
                        Intent intent1 = new Intent(Profile.this, WorkOut.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_dietplan:
                        Intent intent2 = new Intent(Profile.this, DietPlan.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_calendar:
                        Intent intent3 = new Intent(Profile.this, Calendar.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(Profile.this, Profile.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });


    }

    private void updateUserInfo() {

    }

    private void enterEditProfile() {
        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("name", newUserName);
        intent.putExtra("age", newAge);
        intent.putExtra("height", newHeight);
        intent.putExtra("weight", newWeight);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}

