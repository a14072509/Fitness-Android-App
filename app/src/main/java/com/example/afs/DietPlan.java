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
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

public class DietPlan extends AppCompatActivity {

    private ImageButton addFoodButton;
    private ListView foodList;
    private ImageButton deleteButton;
    private boolean deleteMode = false;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private String userID;
    private List<Food> food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_plan);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        foodList = (ListView)findViewById(R.id.todayFoodList);

        food = new ArrayList<Food>();


        //TODO Retrieve from database the food of today.
        db = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();

        db.child("Users").child(userID).child(MainActivity.toDate)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String foodListStr = dataSnapshot.child("food_list").getValue().toString();
                System.out.println(foodListStr);
                if(foodListStr.length() <= 4) {
                    food = null;
                }
                else {
                    food = parseStrToFoodlist(foodListStr);
                    FoodAdapter foodAdapter = new FoodAdapter(DietPlan.this, food);
                    foodList.setAdapter(foodAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Tests
        //food.add(new Food("Apple", 500));
        //food.add(new Food("Pear", 300));
        //food.add(new Food("Banana", 200));

        //FoodAdapter foodAdapter = new FoodAdapter(this, food);
        //foodList.setAdapter(foodAdapter);

        deleteButton = (ImageButton)findViewById(R.id.deleteFoodButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteMode = true;
            }
        });

        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        addFoodButton = (ImageButton)findViewById(R.id.addFoodButton);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addFood();
            }
        });

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_workout:
                        Intent intent1 = new Intent(DietPlan.this, WorkOut.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_dietplan:
                        Intent intent2 = new Intent(DietPlan.this, DietPlan.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_calendar:
                        Intent intent3 = new Intent(DietPlan.this, Calendar.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(DietPlan.this, Profile.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    public void addFood() {
        Intent intent = new Intent(this, FoodHistory.class);
        startActivity(intent);
    }

    public ArrayList<Food> parseStrToFoodlist(String foodStr) {
        ArrayList<Food> parsedList = new ArrayList<Food>();
        int len = foodStr.length();
        foodStr = foodStr.substring(5, len - 1);
        //System.out.println(foodStr);
        while(foodStr != "") {
            String[] nameTemp = foodStr.split("=", 2);
            //System.out.println("nameaaaa\n"+nameTemp[0]+"split"+nameTemp[1]);
            if(!nameTemp[1].contains("=")) {
                parsedList.add(new Food(nameTemp[0], Integer.parseInt(nameTemp[1])));
                return parsedList;
            }
            String[] caloTemp = nameTemp[1].split(", ", 2);
            //System.out.println("caloaaaa\n"+caloTemp[0]+"divide"+caloTemp[1]);
            parsedList.add(new Food(nameTemp[0], Integer.parseInt(caloTemp[0])));
            foodStr = caloTemp[1];
        }
        return parsedList;
    }
}

