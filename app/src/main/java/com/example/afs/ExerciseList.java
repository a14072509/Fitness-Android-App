package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ExerciseList extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private ListView exerList;
    private ArrayList<Exercise> exercisesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_list);

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


        exercisesDB = new ArrayList<Exercise>();

        //TODO Read the list


        //These are jsut tests
        exercisesDB.add(new Exercise( "bird-dog","abs14"));
        exercisesDB.add(new Exercise( "BOSU Squat Jumps", "abs117"));
        exercisesDB.add(new Exercise("BOSU Lateral Jumps", "abs119" ));
        exercisesDB.add(new Exercise( "Bodyweight Squat", "abs135"));

        //store the list into array
        updateExerciseAdapter(exercisesDB);

        exerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exercise clickedObj =  (Exercise)parent.getItemAtPosition(position);
                exerDetail(clickedObj);

            }
        });

    }

    private void back() {
        Intent intent = new Intent(this, WorkOut.class);
        startActivity(intent);
    }

    private void exerDetail(Exercise e) {
        Intent intent = new Intent(this, EqptInfo.class);
        intent.putExtra("gif", e.getGifPath());
        intent.putExtra("name", e.getName());
        intent.putExtra("eqpt", e.getEquipment());
        intent.putExtra("instruction", e.getInstructions());
        intent.putExtra("image", e.getImagePath());
        startActivity(intent);
    }
    private void updateExerciseAdapter(ArrayList<Exercise> exerciseList)
    {
        //store the list into array

        //get the food list gadget
        exerList = (ListView)findViewById(R.id.exercise_list);

        //set up the adapter
        ExerciseAdapter exerciseAdapter= new ExerciseAdapter(getApplicationContext(), exerciseList);
        exerList.setAdapter(exerciseAdapter);
    }

}
