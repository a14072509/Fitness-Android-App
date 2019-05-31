package com.example.afs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

public class FoodAdapter extends BaseAdapter {
    Context context;
    List<Food> food;
    LayoutInflater inflter;
    private String mode;

    public FoodAdapter(Context applicationContext, List<Food> food, String mode) {
        this.context = applicationContext;
        this.food = food;
        inflter = (LayoutInflater.from(applicationContext));
        this.mode = mode;
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
        if(!mode.equals("c"))
            view = inflter.inflate(R.layout.food_text, null);
        else
            view = inflter.inflate(R.layout.calender_list_item, null);

        TextView name = (TextView) view.findViewById(R.id.foodTextView);
        TextView calorie = (TextView) view.findViewById(R.id.calorieTextView);
        name.setText(food.get(i).getName());
        calorie.setText(""+food.get(i).getCalorie());
        return view;
    }
}