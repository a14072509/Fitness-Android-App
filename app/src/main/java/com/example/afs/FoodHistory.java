package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.*;

public class FoodHistory extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Button addItemButton;
    private ListView foodList;
    private List<Food> foodDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_history);


        foodDB = new ArrayList<Food>();

        //TODO Read the list of names and calories from database, store in namesDB and caloriesDB


        //These are jsut tests
        foodDB.add(new Food("Apple", 300));

        //store the list into array
        updateFoodAdapter(foodDB);

        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food clickedObj =  (Food)parent.getItemAtPosition(position);
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

    private void updateFoodAdapter(List<Food> food)
    {
        //get the food list gadget
        foodList = (ListView)findViewById(R.id.foodHistoryList);

        //set up the adapter
        FoodAdapter foodAdapter= new FoodAdapter(getApplicationContext(), food);
        foodList.setAdapter(foodAdapter);
    }

    private void addFood(Food f) {

        //TODO add the food selected to the database
    }
}
