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
    private Button addItemButton;
    private ListView foodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_history);

        foodList = (ListView)findViewById(R.id.foodHistoryList);

        //ArrayList<String> names = new ArrayList<String>();
        //ArrayList<Integer> calories = new ArrayList<Integer>();

        String[] temp = new String[]{"Apple", "Banana"};
        Integer[] temp1 = new Integer[]{300, 250};

        //FoodAdapter adapter = new FoodAdapter(this, temp, temp1);
        //foodList.setAdapter(adapter);

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.food_history, R.id.textView, temp);
        //foodList.setAdapter(arrayAdapter);

        addItemButton = (Button) findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addItem();
            }
        });
    }


    public void addItem() {
        Intent intent = new Intent(this, AddFood.class);
        startActivity(intent);
    }
}
