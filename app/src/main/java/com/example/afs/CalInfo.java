package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

public class CalInfo extends firebaseActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Date date;
    private History hist;
    private ListView foodList;
    private ListView exerList;
    private TextView takenCalorie;
    private TextView burntCalorie;
    public static TextView resultCalorie;
    private List<Food> food;
    private List<Food> exer;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private String userID;
    private int caloriesTaken;
    private int caloriesBurned;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calandar_info);

        String dateText = getIntent().getStringExtra("date");

        System.out.println(dateText);

        db = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();

        final String newDateText = parseDate(dateText);

        db.child("Users").child(userID).child(newDateText).child("food_list").child(" ").setValue("");
        db.child("Users").child(userID).child(newDateText).child("exercise_list").child(" ").setValue("");


        db.child("Users").child(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String foodListStr = dataSnapshot.child(newDateText).child("food_list").getValue().toString();
                        String exerListStr = dataSnapshot.child(newDateText).child("exercise_list").getValue().toString();


                        //System.out.println(foodListStr);
                        if(foodListStr.length() <= 4) {
                            food = null;
                        }
                        else {
                            food = parseStrToFoodlist(foodListStr);
                            caloriesTaken = calculateCalories(foodListStr);
                            foodList = (ListView)findViewById(R.id.taken_calorie_list);
                            takenCalorie = (TextView)findViewById(R.id.taken_calorie);
                            FoodAdapter foodAdapter = new FoodAdapter(getApplicationContext(), food, "c",false);
                            foodList.setAdapter(foodAdapter);
                            takenCalorie.setText("" + caloriesTaken);

                        }

                        if(exerListStr.length() <= 4) {
                            exer = null;
                        }
                        else {
                            exer = parseStrToFoodlist(exerListStr);
                            caloriesBurned = calculateCalories(exerListStr);
                            exerList = (ListView)findViewById(R.id.burnt_calorie_list);
                            burntCalorie = (TextView)findViewById(R.id.burnt_calorie);
                            FoodAdapter exerAdapter = new FoodAdapter(getApplicationContext(), exer, "c", false);
                            exerList.setAdapter(exerAdapter);
                            burntCalorie.setText("" + caloriesBurned);

                        }

                        resultCalorie = (TextView)findViewById(R.id.result_calorie);
                        resultCalorie.setText("" + (caloriesTaken - caloriesBurned));

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        String[] text = dateText.split(" ");

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.calInfoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        getSupportActionBar().setTitle(text[0] + " " + text[1] + ", " + text[2]);
    }

    private void back() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
