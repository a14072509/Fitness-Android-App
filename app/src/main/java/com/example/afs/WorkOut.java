package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class WorkOut extends AppCompatActivity {

    private Button aerobicButton;
    private ImageButton flipButton;
    private RelativeLayout maleFront;
    private RelativeLayout maleBack;
    private RelativeLayout femaleFront;
    private RelativeLayout femaleBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);

        aerobicButton = (Button) findViewById(R.id.aerobic);
        aerobicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterExerciseList("Leg");
            }
        });

        // todo: can add state variable to track
        flipButton = (ImageButton) findViewById(R.id.flip_button);
        maleFront = (RelativeLayout) findViewById(R.id.male_front_section);
        maleBack = (RelativeLayout) findViewById(R.id.male_back_section);
        femaleFront = (RelativeLayout) findViewById(R.id.female_front_section);
        femaleBack = (RelativeLayout) findViewById(R.id.female_back_section);
        flipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                maleFront.setVisibility(View.GONE);
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_workout:
                        Intent intent1 = new Intent(WorkOut.this, WorkOut.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_dietplan:
                        Intent intent2 = new Intent(WorkOut.this, DietPlan.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_calendar:
                        Intent intent3 = new Intent(WorkOut.this, Calendar.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(WorkOut.this, Profile.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void enterExerciseList(String body) {
        Intent intent = new Intent(this, ExerciseList.class);
        System.out.println(body);
        intent.putExtra("body", body);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}



