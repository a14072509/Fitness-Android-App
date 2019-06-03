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
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.List;

public class Calendar extends firebaseActivity {
    private Button calinfo;
    private CalendarView calenderView;
    private String selDateText;
    private TextView dailyCalorie;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private String userID;
    private List<Food> food;
    private List<Food> exer;
    private int caloriesTaken;
    private int caloriesBurned;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        db = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();


        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calenderView = (CalendarView) findViewById(R.id.calendarView);

        Date selDate = calendar.getTime();
        String temp = selDate.toString();
        temp = temp.substring(temp.lastIndexOf(" ")+1);

        selDateText = getMonthString(selDate.getMonth()+1) + " " + selDate.getDate() + " " + temp;
        calinfo = (Button) findViewById(R.id.detail);
        calinfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterCalInfo();
            }
        });

        calenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                selDateText = getMonthString(month+1) + " " + dayOfMonth + " " + year;
                System.out.println("test date\n"+selDateText);
            }
        });

        db.child("Users").child(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        int age = Integer.parseInt(dataSnapshot.child("age").getValue().toString());
                        double height = Double.parseDouble(dataSnapshot.child("height").getValue().toString());
                        double weight = Double.parseDouble(dataSnapshot.child("weight").getValue().toString());
                        Gender gender = Gender.valueOf(dataSnapshot.child("Gender").getValue().toString());

                        double BMR;
                        if(gender == Gender.FEMALE)
                        {
                            BMR = 655 + 4.3 * weight + 4.7 * 12 * height - 4.7 * age;
                        }
                        else
                        {
                            BMR = 66 + 6.3 * weight + 12.9 * 12 * height - 6.8 * age;
                        }

                        String foodListStr = dataSnapshot.child(MainActivity.toDate)
                                .child("food_list").getValue().toString();
                        String exerListStr = dataSnapshot.child(MainActivity.toDate)
                                .child("exercise_list").getValue().toString();


                        //System.out.println(foodListStr);
                        if(foodListStr.length() <= 4) {
                            food = null;
                        }
                        else {
                            food = parseStrToFoodlist(foodListStr);
                            caloriesTaken = calculateCalories(foodListStr);
                        }

                        if(exerListStr.length() <= 4) {
                            exer = null;
                        }
                        else {
                            exer = parseStrToFoodlist(exerListStr);
                            caloriesBurned = calculateCalories(exerListStr);
                        }

                        //System.out.println(foodListStr);

                        dailyCalorie = (TextView)findViewById(R.id.daily_cal);
                        String dailyCalorieText = String.format("%.2f", (caloriesTaken - caloriesBurned - BMR));
                        dailyCalorie.setText(dailyCalorieText);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void enterCalInfo() {
        Intent intent = new Intent(this, CalInfo.class);
        intent.putExtra("date", selDateText);
        startActivity(intent);
    }


    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private String getMonthString(int month)
    {
        switch(month)
        {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        return "Unknown";
    }
}

