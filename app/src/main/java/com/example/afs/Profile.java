package com.example.afs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.IOException;


public class Profile extends AppCompatActivity {
    private Button logOutButton;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    FirebaseStorage storage;
    StorageReference storageReference;
    private ImageButton editButton;
    public static ImageView photo;
    private ImageView genderIcon;
    private TextView ageText;
    private TextView heightText;
    private TextView weightText;
    private TextView usernameText;
    private TextView BMRText;
    private String userID;
    private String newUserName;
    private String newAge;
    private String newHeight;
    private String newWeight;
    private Gender newGender;
    private String path;
    private static final String TAG = "Profile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        db = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();

        usernameText = (TextView) findViewById(R.id.Username);
        ageText = (TextView) findViewById(R.id.edit_age);
        heightText = (TextView) findViewById(R.id.edit_height);
        weightText = (TextView) findViewById(R.id.edit_weight);
        BMRText = (TextView) findViewById(R.id.BMR);
        photo = (ImageView) findViewById(R.id.photo);
        genderIcon = (ImageView) findViewById(R.id.male_icon);


        db.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newUserName = (String)dataSnapshot.child("userName").getValue();
                //System.out.println("Sava Method: \n" + dataSnapshot.getValue());
                //System.out.println("Sava Method asdfasdf: \n" + dataSnapshot.child("userName").getValue());
                newAge = dataSnapshot.child("age").getValue().toString();
                newHeight = dataSnapshot.child("height").getValue().toString();
                newWeight = dataSnapshot.child("weight").getValue().toString();
                newGender = Gender.valueOf(dataSnapshot.child("Gender").getValue().toString());

                updateBMI(newGender, newAge, newHeight, newWeight);
                //path = dataSnapshot.child("Photo_Path").getValue().toString();
                //System.out.println("aaa\n\n"+path);

                storageReference = storage
                        .getReferenceFromUrl("gs://abbt-a95ad.appspot.com/images/" + userID);
                final long ONE_MEGABYTE = 1024 * 1024;
                storageReference.getBytes(ONE_MEGABYTE)
                        .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                photo.setImageBitmap(bm);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                            }
                        });
                //Glide.with(Profile.this).load(storageReference).into(photo);
                //System.out.println("AAABBBCCC\n" + newUserName);
                usernameText.setText(newUserName);
                ageText.setText(newAge);
                heightText.setText(newHeight);
                weightText.setText(newWeight);
                if(newGender.toString() == "FEMALE") {
                    genderIcon.setImageResource(R.drawable.female_icon);
                }
                else genderIcon.setImageResource(R.drawable.male_icon);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        logOutButton = (Button) findViewById(R.id.logout_button);
        logOutButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Profile.this, login.class);
                startActivity(intent);
            }
        });

        editButton = (ImageButton) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterEditProfile();
            }
        });



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_workout:
                        Intent intent1 = new Intent(Profile.this, WorkOut.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_dietplan:
                        Intent intent2 = new Intent(Profile.this, DietPlan.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_calendar:
                        Intent intent3 = new Intent(Profile.this, Calendar.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(Profile.this, Profile.class);
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

    private void enterEditProfile() {
        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("name", newUserName);
        intent.putExtra("age", newAge);
        intent.putExtra("height", newHeight);
        intent.putExtra("weight", newWeight);
        if(newGender == Gender.FEMALE)
            intent.putExtra("gender", "f");
        else
            intent.putExtra("gender", "m");
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void updateBMI(Gender gender, String age, String height, String weight)
    {
        try {
            int ageV = Integer.parseInt(age);
            double heightV = Double.parseDouble(height);
            double weightV = Double.parseDouble(weight);

            String BMRStr;
            if(gender == Gender.FEMALE)
            {
                double BMR = 655 + 4.3 * weightV + 4.7 * 12 * heightV - 4.7 * ageV;
                BMRStr = String.format("%.2f", BMR);
            }
            else
            {
                double BMR = 66 + 6.3 * weightV + 12.9 * 12 * heightV - 6.8 * ageV;
                BMRStr = String.format("%.2f", BMR);
            }
            BMRText.setText(BMRStr);
        }
        catch(Exception e){}

    }

}

