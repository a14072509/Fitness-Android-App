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

import java.util.*;

public class DietPlan extends AppCompatActivity {

    private ImageButton addFoodButton;
    private ListView foodList;
    private ImageButton deleteButton;
    private boolean deleteMode = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_plan);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        foodList = (ListView)findViewById(R.id.todayFoodList);

        List<Food> food = new ArrayList<Food>();
        //TODO Retrieve from database the food of today.

        //Tests
        food.add(new Food("Apple", 500));
        food.add(new Food("Pear", 300));
        food.add(new Food("Banana", 200));

        FoodAdapter foodAdapter = new FoodAdapter(this, food);
        foodList.setAdapter(foodAdapter);

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
}

