package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.Map;

public class CalInfo extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Date date;
    private History hist;
    private ListView foodList;
    private ListView exerList;
    private TextView takenCalorie;
    private TextView burntCalorie;
    private TextView resultCalorie;
    private String[] foodNames;
    private String[] foodCalorie;
    private String[] exerNames;
    private String[] exerCalorie;



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
        Map<String, Integer> food = hist.getFood();
        Map<String, Integer> exer = hist.getExercise();

        foodNames = new String[food.size()];
        foodCalorie = new String[food.size()];
        exerNames = new String[exer.size()];
        exerCalorie = new String[exer.size()];
        int i = 0;
        for(String f : food.keySet()) {
            foodNames[i] = f;
            foodCalorie[i] = ""+food.get(f);
            i++;
        }
        i = 0;
        for(String e : exer.keySet()) {
            exerNames[i] = e;
            exerCalorie[i] = ""+exer.get(e);
            i++;
        }
        foodList = (ListView)findViewById(R.id.taken_calorie_list);
        takenCalorie = (TextView)findViewById(R.id.taken_calorie);
        FoodAdapter foodAdapter = new FoodAdapter(getApplicationContext(), foodNames, foodCalorie);
        foodList.setAdapter(foodAdapter);
        takenCalorie.setText("" + hist.getCalTaken());

        exerList = (ListView)findViewById(R.id.burnt_calorie_list);
        burntCalorie = (TextView)findViewById(R.id.burnt_calorie);
        FoodAdapter exerAdapter = new FoodAdapter(getApplicationContext(), exerNames, exerCalorie);
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
