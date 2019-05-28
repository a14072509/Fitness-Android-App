package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFood extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Button addButton;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);

        //initialize database
        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        //user must log in
        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                add();
            }
        });

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.addFoodToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {back();
            }
        });
    }

    private void back() {
        Intent intent = new Intent(this, FoodHistory.class);
        startActivity(intent);
    }

    /**
     * Add the food to the database based on user input
     */
    public void add() {
        EditText foodName = (EditText)findViewById(R.id.foodName);
        EditText foodCalorie = (EditText)findViewById(R.id.foodCalorie);

        try {

            //make sure the input calorie is valid, otherwise, reset the calorie input box
            String name = foodName.getText().toString();
            int calorie = Integer.parseInt(foodCalorie.getText().toString());
            if(calorie < 0 || name.length() == 0)
                throw new NumberFormatException();

            //TODO Need to add the food information into database
            String userID = curUser.getUid();
            db.child("Users").child(userID).child("food_list").child("name").push();


            //After updating database, go back to the previous page
            finish();

        } catch(NumberFormatException e) {
            foodCalorie.setText("");
            return;
        }


    }
}
