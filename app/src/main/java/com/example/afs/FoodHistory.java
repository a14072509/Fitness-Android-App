package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodHistory extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Button addItemButton;
    public ListView foodList;
    public String[] names;
    public String[] calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_history);

        foodList = (ListView)findViewById(R.id.foodHistoryList);

        ArrayList<String> namesDB = new ArrayList<String>();
        ArrayList<String> caloriesDB = new ArrayList<String>();

        //TODO Read names and calories from database


        //These are jsut tests
        namesDB.add("Apple");
        namesDB.add("Bag");
        caloriesDB.add(""+200);
        caloriesDB.add(""+300);


        //store the list into array
        names = new String[namesDB.size()];
        calories = new String[caloriesDB.size()];
        namesDB.toArray(names);
        caloriesDB.toArray(calories);

        //get the food list gadget
        foodList = (ListView)findViewById(R.id.foodHistoryList);

        //set up the adapter
        FoodAdapter foodAdapter= new FoodAdapter(getApplicationContext(), names, calories);
        foodList.setAdapter(foodAdapter);


        addItemButton = (Button) findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
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
        Intent intent = new Intent(this, DietPlan.class);
        startActivity(intent);
    }
}
