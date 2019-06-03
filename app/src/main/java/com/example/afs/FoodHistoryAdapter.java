package com.example.afs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.*;

public class FoodHistoryAdapter extends BaseAdapter {
    Context context;
    List<Food> food;
    LayoutInflater inflter;

    public FoodHistoryAdapter(Context applicationContext, List<Food> food) {
        this.context = applicationContext;
        this.food = food;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return food.size();
    }

    @Override
    public Object getItem(int position) {
        return food.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.food_history_list_item, null);

        TextView name = (TextView) view.findViewById(R.id.foodTextView);
        TextView calorie = (TextView) view.findViewById(R.id.calorieTextView);
        name.setText(food.get(i).getName());
        calorie.setText(""+food.get(i).getCalorie());
        return view;
    }
}