package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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

public class DietPlan extends firebaseActivity {

    private ImageButton addFoodButton;
    private ListView foodList;
    private ImageButton deleteButton;
    private TextView totalCalText;
    private boolean deleteMode = false;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private String userID;
    private List<Food> food;
    private int totalCalories;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_plan);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        foodList = (ListView)findViewById(R.id.todayFoodList);


        food = new ArrayList<Food>();

        totalCalText = (TextView)findViewById(R.id.total_calories);


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
                //System.out.println(foodListStr);
                if(foodListStr.length() <= 4) {
                    foodList.removeFooterView(foodList);
                    //TODO refresh the current page
                    
                }
                else {
                    food = parseStrToFoodlist(foodListStr);
                    totalCalories = calculateCalories(foodListStr);
                    totalCalText.setText(String.valueOf(totalCalories));
                    DietFoodAdapter foodAdapter = new DietFoodAdapter(DietPlan.this, food, deleteMode);
                    foodList.setAdapter(foodAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        deleteButton = (ImageButton)findViewById(R.id.deleteFoodButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteMode = true;
                DietFoodAdapter foodAdapter = new DietFoodAdapter(DietPlan.this, food, deleteMode);
                foodList.setAdapter(foodAdapter);

                deleteButton.setVisibility(View.INVISIBLE);
                addFoodButton.setVisibility(View.INVISIBLE);
                doneButton.setVisibility(View.VISIBLE);
            }
        });

        doneButton = (Button)findViewById(R.id.done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteButton.setVisibility(View.VISIBLE);
                addFoodButton.setVisibility(View.VISIBLE);
                deleteMode = false;
                DietFoodAdapter foodAdapter = new DietFoodAdapter(DietPlan.this, food, deleteMode);
                foodList.setAdapter(foodAdapter);
                doneButton.setVisibility(View.INVISIBLE);
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

        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food clickedObj =  (Food)parent.getItemAtPosition(position);
                System.out.println("Hi");
                if(deleteMode)
                    deleteFood(clickedObj);
            }
        });

        deleteButton.setVisibility(View.VISIBLE);
        addFoodButton.setVisibility(View.VISIBLE);
        doneButton.setVisibility(View.INVISIBLE);


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
    public void onBackPressed() {
        moveTaskToBack(true);
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

    private void deleteFood(Food f){
        System.out.println("Deleting");
        System.out.println(f.getName());
        //TODO remove the food from today's history list
        System.out.println("test312313\n"+f.getName());
        db.child("Users").child(userID).child(MainActivity.toDate).child("food_list").child(f.getName()).removeValue();
    }

}

