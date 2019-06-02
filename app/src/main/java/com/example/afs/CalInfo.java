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
    private TextView resultCalorie;
    private TextView dateView;
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
        dateView = (TextView)findViewById(R.id.date);
        dateView.setText(dateText);

        db = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();

        dateText = parseDate(dateText);

        db.child("Users").child(userID).child(dateText).child("food_list").child(" ").setValue("");
        db.child("Users").child(userID).child(dateText).child("exercise_list").child(" ").setValue("");


        db.child("Users").child(userID).child(dateText)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String foodListStr = dataSnapshot.child("food_list").getValue().toString();
                        String exerListStr = dataSnapshot.child("exercise_list").getValue().toString();
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

        //test
        //hist = new History(date, 500);

        //hist.addFood("Apple", 500);
        //hist.addFood("Pear", 20);
        //hist.addExercise("Bird-dog", 1000);
        //hist.addExercise("Push_up", 300);
        //hist.addExercise("Running", 500);
        //hist.addExercise("Pull-up", 300);

        //set up the adapters
        //Map<String, Integer> foodDB = hist.getFood();
        //Map<String, Integer> exerDB = hist.getExercise();

        //food = new ArrayList<Food>();
        //exer = new ArrayList<Food>();

        //for(String f : foodDB.keySet())
            //food.add(new Food(f, foodDB.get(f)));
        //for(String e : exerDB.keySet())
        //    exer.add(new Food(e, exerDB.get(e)));

        /*foodList = (ListView)findViewById(R.id.taken_calorie_list);
        takenCalorie = (TextView)findViewById(R.id.taken_calorie);
        FoodAdapter foodAdapter = new FoodAdapter(getApplicationContext(), food);
        foodList.setAdapter(foodAdapter);
        takenCalorie.setText("" + hist.getCalTaken());*/

        /*exerList = (ListView)findViewById(R.id.burnt_calorie_list);
        burntCalorie = (TextView)findViewById(R.id.burnt_calorie);
        FoodAdapter exerAdapter = new FoodAdapter(getApplicationContext(), exer, "c");
        exerList.setAdapter(exerAdapter);
        burntCalorie.setText("" + hist.getCalSpent());*/

        //resultCalorie = (TextView)findViewById(R.id.result_calorie);
        //resultCalorie.setText("" + );

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.calInfoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {back();
            }
        });
    }

    private void back() {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

}
