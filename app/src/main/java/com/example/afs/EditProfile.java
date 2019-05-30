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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.lang.Double;
import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    private Button save;
    private EditText ageText;
    private EditText heightText;
    private EditText weightText;
    private EditText usernameText;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        usernameText = (EditText) findViewById(R.id.Username);
        ageText = (EditText) findViewById(R.id.edit_age);
        heightText = (EditText) findViewById(R.id.edit_height);
        weightText = (EditText) findViewById(R.id.edit_weight);

        usernameText.setText(getIntent().getStringExtra("name"));
        ageText.setText(getIntent().getStringExtra("age"));
        heightText.setText(getIntent().getStringExtra("height"));
        weightText.setText(getIntent().getStringExtra("weight"));



        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { enterProfile();
            }
        });

    }

    private void enterProfile() {
        int age = 0;
        double height = 0;
        double weight = 0;
        String userID = curUser.getUid();


        String username = usernameText.getText().toString();
        db.child("Users").child(userID).child("userName").setValue(username);


        String ageStr = ageText.getText().toString();
        try {
            age = Integer.parseInt(ageStr);
        }catch (NumberFormatException e){
            System.out.println("Not a number");
        }

        String heightStr = heightText.getText().toString();
        try {
            height = Double.parseDouble(heightStr);
        }catch (NumberFormatException e){
            System.out.println("Not a number");
        }


        String weightStr = weightText.getText().toString();
        try {
            weight = Double.parseDouble(weightStr);
        }catch (NumberFormatException e){
            System.out.println("Not a number");
        }

        FirebaseUser fUser = mAuth.getCurrentUser();
        UserInfo user = new UserInfo(username, weight, height, Gender.UNKNOWN, age, fUser.getEmail());
        Map<String, Object> userInfoMap = user.toMap();
        db.child("Users").child(userID).updateChildren(userInfoMap);


        finish();
    }

}
