package com.example.afs;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExerciseAdapter extends BaseAdapter {
    Context context;
    String[] images;
    String[] exercises;
    LayoutInflater inflter;

    public ExerciseAdapter(Context applicationContext,String[] images, String[] exercises) {
        this.context = applicationContext;
        this.images = images;
        this.exercises = exercises;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return exercises.length;
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
        view = inflter.inflate(R.layout.exercise_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.exercise_image);
        TextView exercise = (TextView) view.findViewById(R.id.exercise_name);
        Resources res = context.getResources();
        String name = images[i]+"_1";
        System.out.println(name);
        int resID = res.getIdentifier(name , "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID );
        image.setImageDrawable(drawable );
        exercise.setText(exercises[i]);
        return view;
    }
}