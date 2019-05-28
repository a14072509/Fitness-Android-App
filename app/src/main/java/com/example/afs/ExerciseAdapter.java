package com.example.afs;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ExerciseAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    Context context;
    List<Exercise> exercises;
    LayoutInflater inflter;

    public ExerciseAdapter(Context applicationContext,List<Exercise> exercises) {
        this.context = applicationContext;
        this.exercises = exercises;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        if(exercises == null)
            return 0;
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
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
        String name = exercises.get(i).getImagePath()+"_1";
        System.out.println(name);
        int resID = res.getIdentifier(name , "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID );
        image.setImageDrawable(drawable );
        exercise.setText(exercises.get(i).getName());
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(parent.getItemAtPosition(position));
    }
}