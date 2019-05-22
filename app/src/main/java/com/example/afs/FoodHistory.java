package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodHistory extends AppCompatActivity {
    private Button addItemButton;
    private ScrollView foodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_history);

        foodList = (ScrollView)findViewById(R.id.foodHistoryList);

        //ArrayList<String> names = new ArrayList<String>();
        //ArrayList<Integer> calories = new ArrayList<Integer>();

        String[] temp = new String[]{"Apple", "Banana"};
        Integer[] temp1 = new Integer[]{300, 250};

        //FoodAdapter adapter = new FoodAdapter(this, temp, temp1);
        //foodList.setAdapter(adapter);

        TextView textView = new TextView(this);
        textView.setText("Some text");
        foodList.addView(textView);

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
