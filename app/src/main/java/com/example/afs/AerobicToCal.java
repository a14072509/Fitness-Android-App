package com.example.afs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AerobicToCal extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private EditText input1;
    private EditText input2;
    private Button addExer;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private String userID;
    private int calorieNum;
    private int cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_aerobic);

        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();


        input1 = (EditText)findViewById(R.id.input1);
        input2 = (EditText)findViewById(R.id.input2);
        addExer = (Button)findViewById(R.id.aerobic_add_button);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.aerobicToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Aerobic Exercise");

        addExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food food = new Food(input1.getText().toString(), Integer.parseInt(input2.getText().toString()));
                add(food);
                finish();
            }
        });
    }

    private void add(final Food f) {
        db.child("Users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    calorieNum = Integer.parseInt(dataSnapshot.child(MainActivity.toDate).child("exercise_list")
                            .child(f.getName()).getValue().toString());
                    //System.out.println("testname\n" + f.getName());
                    //System.out.println("test\n"+calorieNum);
                    cal = calorieNum + f.getCalorie();
                    db.child("Users").child(userID).child(MainActivity.toDate).child("exercise_list")
                            .child(f.getName()).setValue(cal);
                }
                //System.out.println("etasdfas\n"+f.getCalorie());

                catch (Exception e) {
                    //System.out.println("testname\n" + f.getName());
                    //System.out.println("test\n"+calorieNum);
                    db.child("Users").child(userID).child(MainActivity.toDate).child("exercise_list")
                            .child(f.getName()).setValue(f.getCalorie());
                }
                //System.out.println("etes\n"+calorieNum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
