package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

import java.util.Date;

public class Calendar extends AppCompatActivity {
    private Button calinfo;
    private CalendarView calenderView;
    private int selYear;
    private int selMonth;
    private int selDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);


        calinfo = (Button) findViewById(R.id.detail);
        calinfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterCalInfo();
            }
        });
        calenderView = (CalendarView) findViewById(R.id.calendarView);

        calenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                selYear = year;
                selMonth = month;
                selDay = dayOfMonth;
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_workout:
                        Intent intent1 = new Intent(Calendar.this, WorkOut.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_dietplan:
                        Intent intent2 = new Intent(Calendar.this, DietPlan.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_calendar:
                        Intent intent3 = new Intent(Calendar.this, Calendar.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(Calendar.this, Profile.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
    }

    private void enterCalInfo() {
        Intent intent = new Intent(this, CalInfo.class);
        intent.putExtra("year", selYear);
        intent.putExtra("month", selMonth);
        intent.putExtra("day", selDay);
        startActivity(intent);
    }


    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}

