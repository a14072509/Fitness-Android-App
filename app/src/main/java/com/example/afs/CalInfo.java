package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.*;

public class CalInfo extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Date date;
    private History hist;
    private ListView foodList;
    private ListView exerList;
    private TextView takenCalorie;
    private TextView burntCalorie;
    private TextView resultCalorie;
    private List<Food> food;
    private List<Food> exer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calandar_info);

        int year = getIntent().getIntExtra("year", 0);
        int month = getIntent().getIntExtra("month", 0);
        int day = getIntent().getIntExtra("day", 0);
        date = new Date(year, month, day);

        //TODO retrieve the history from database and store in hist declared on top

        //test
        hist = new History(date, 500);

        hist.addFood("Apple", 500);
        hist.addFood("Pear", 20);
        hist.addExercise("Bird-dog", 1000);
        hist.addExercise("Push_up", 300);
        hist.addExercise("Running", 500);

        //set up the adapters
        Map<String, Integer> foodDB = hist.getFood();
        Map<String, Integer> exerDB = hist.getExercise();

        food = new ArrayList<Food>();
        exer = new ArrayList<Food>();

        for(String f : foodDB.keySet())
            food.add(new Food(f, foodDB.get(f)));
        for(String e : exerDB.keySet())
            exer.add(new Food(e, exerDB.get(e)));

        foodList = (ListView)findViewById(R.id.taken_calorie_list);
        takenCalorie = (TextView)findViewById(R.id.taken_calorie);
        FoodAdapter foodAdapter = new FoodAdapter(getApplicationContext(), food);
        foodList.setAdapter(foodAdapter);
        takenCalorie.setText("" + hist.getCalTaken());

        exerList = (ListView)findViewById(R.id.burnt_calorie_list);
        burntCalorie = (TextView)findViewById(R.id.burnt_calorie);
        FoodAdapter exerAdapter = new FoodAdapter(getApplicationContext(), exer);
        exerList.setAdapter(exerAdapter);
        burntCalorie.setText("" + hist.getCalSpent());

        resultCalorie = (TextView)findViewById(R.id.result_calorie);
        resultCalorie.setText("" + hist.getResult());

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
