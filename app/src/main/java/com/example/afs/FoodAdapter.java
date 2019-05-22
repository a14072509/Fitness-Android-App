package com.example.afs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodAdapter extends BaseAdapter {
    Context context;
    String[] names;
    Integer[] calories;
    LayoutInflater inflter;

    public FoodAdapter(Context applicationContext, String[] names, Integer[] calories) {
        this.context = context;
        this.names = names;
        this.calories = calories;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.food_history, null);
        TextView name = (TextView) view.findViewById(R.id.foodCalorieTextView);
        //TextView calorie = (TextView) view.findViewById(R.id.foodCalorieTextView);
        name.setText(names[i]);
        //calorie.setText(calories[i]);
        return view;
    }
}