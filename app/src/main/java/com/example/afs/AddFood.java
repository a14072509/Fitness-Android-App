package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddFood extends AppCompatActivity {

    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfood);

        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                add();
            }
        });
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


            //After updating database, go back to the previous page
            finish();

        } catch(NumberFormatException e) {
            foodCalorie.setText("");
            return;
        }


    }
}
