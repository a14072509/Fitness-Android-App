package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.*;

public class FoodHistory extends firebaseActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Button addItemButton;
    private ListView foodList;
    private List<Food> foodDB;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private String userID;
    private RelativeLayout addItemText;
    private int calorieNum;
    private int cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_history);


        foodDB = new ArrayList<Food>();

        //TODO Read the list of names and calories from database, store in namesDB and caloriesDB
        db = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();

        db.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String foodListStr = dataSnapshot.child("foodDB").getValue().toString();
                        //System.out.println(foodListStr);
                        if(foodListStr.length() <= 4) {
                            foodDB = null;
                        }
                        else {
                            foodDB = parseStrToFoodlist(foodListStr);
                            FoodHistoryAdapter foodAdapter = new FoodHistoryAdapter(FoodHistory.this, foodDB);
                            foodList.setAdapter(foodAdapter);
                        }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        //These are just tests
        //foodDB.add(new Food("Apple", 300));

        //store the list into array
        updateFoodAdapter(foodDB);

        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food clickedObj =  (Food)parent.getItemAtPosition(position);
                //System.out.println("tapped\n\n"+clickedObj);
                addFood(clickedObj);
            }
        });

        //the search functionality is implemented here
        SearchView searchView = (SearchView) findViewById(R.id.foodHistorySearchBar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callSearch(newText);
                return true;
            }

            /**
             * It will update the list by checking if the name contains the query
             * @param query The search pattern entered by the user
             */
            public void callSearch(String query) {
                List<Food> temp = new ArrayList<Food>();
                for(int i = 0; i < foodDB.size(); i++) {
                    String curName = foodDB.get(i).getName().toLowerCase();
                    if (curName.indexOf(query.toLowerCase()) != -1) {
                        temp.add(foodDB.get(i));
                    }
                }
                updateFoodAdapter(temp);
            }

        });

        /*addItemButton = (Button) findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addItem();
            }
        });*/

        addItemText = (RelativeLayout) findViewById(R.id.add);
        addItemText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addItem();
            }
        });

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.foodHistoryToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }


    private void addItem() {
        Intent intent = new Intent(this, AddFood.class);
        startActivity(intent);
    }

    private void back() {
        finish();
    }

    private void updateFoodAdapter(List<Food> food)
    {
        //get the food list gadget
        foodList = (ListView)findViewById(R.id.foodHistoryList);

        //set up the adapter
        FoodHistoryAdapter foodAdapter= new FoodHistoryAdapter(getApplicationContext(), food);
        foodList.setAdapter(foodAdapter);
    }

    private void addFood(final Food f) {
        //TODO add the food selected to the database
        db.child("Users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    calorieNum = Integer.parseInt(dataSnapshot.child(MainActivity.toDate).child("food_list")
                            .child(f.getName()).getValue().toString());
                    //System.out.println("testname\n" + f.getName());
                    //System.out.println("test\n"+calorieNum);
                    cal = calorieNum + f.getCalorie();
                    db.child("Users").child(userID).child(MainActivity.toDate).child("food_list")
                            .child(f.getName()).setValue(cal);
                }
                //System.out.println("etasdfas\n"+f.getCalorie());

                catch (Exception e) {
                    //System.out.println("testname\n" + f.getName());
                    //System.out.println("test\n"+calorieNum);
                    db.child("Users").child(userID).child(MainActivity.toDate).child("food_list")
                            .child(f.getName()).setValue(f.getCalorie());
                }
                //System.out.println("etes\n"+calorieNum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*else {
            db.child("Users").child(userID).child(MainActivity.toDate).child("food_list")
                    .child(f.getName()).setValue()
        }*/
        finish();

    }
}
