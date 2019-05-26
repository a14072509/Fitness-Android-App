package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class ExerciseList extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private ListView exerList;
    private String[] exercises;
    private String[] images;
    private ArrayList<String> exercisesDB;
    private ArrayList<String> imagesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exerciselist);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.ExerciseListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });


        exercisesDB = new ArrayList<String>();
        imagesDB = new ArrayList<String>();

        //TODO Read the list


        //These are jsut tests

        imagesDB.add("abs14");
        imagesDB.add("abs117");
        imagesDB.add("abs119");
        imagesDB.add("abs135");
        exercisesDB.add("bird-dog");
        exercisesDB.add("BOSU Squat Jumps");
        exercisesDB.add("BOSU Lateral Jumps");
        exercisesDB.add("Bodyweight Squat");



        //store the list into array
        updateExerciseAdapter(imagesDB, exercisesDB);


    }

    private void back() {
        Intent intent = new Intent(this, WorkOut.class);
        startActivity(intent);
    }

    private void updateExerciseAdapter(ArrayList<String> imageList, ArrayList<String> exerciseList)
    {
        //store the list into array
        images = new String[imageList.size()];
        exercises = new String[exerciseList.size()];
        imageList.toArray(images);
        exerciseList.toArray(exercises);

        //get the food list gadget
        exerList = (ListView)findViewById(R.id.exercise_list);

        //set up the adapter
        ExerciseAdapter exerciseAdapter= new ExerciseAdapter(getApplicationContext(), images, exercises);
        exerList.setAdapter(exerciseAdapter);
    }

}
