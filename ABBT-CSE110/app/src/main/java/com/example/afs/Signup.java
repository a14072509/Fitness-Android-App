package com.example.afs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class Signup extends firebaseActivity {


    private EditText emailInput;
    private EditText confirmInput;
    private EditText passwordInput;
    private Button signUpButton;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        signUpButton = (Button)findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signUp();
            }
        });


        //TODO check with database to
        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onStart()
    {
        super.onStart();

        if(mAuth.getCurrentUser() != null)
        {
            onAuthSuccess(mAuth.getCurrentUser());
        }
    }

    private void signUp()
    {
        emailInput = (EditText)findViewById(R.id.emailInput);
        confirmInput = (EditText)findViewById(R.id.confirmInput);
        passwordInput = (EditText)findViewById(R.id.passwordInput);

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String confirm = confirmInput.getText().toString();

        //basic format check
        int indexOfAt = email.indexOf("@");
        if(indexOfAt == -1 || indexOfAt == 0 ||
                indexOfAt == email.length()-1 || email.indexOf(" ") != -1)
        {
            emailInput.setText("");
            emailInput.setHint("Invalid email format");
            return;
        }
        if(password.length() == 0)
        {
            passwordInput.setText("");
            passwordInput.setHint("Cannot be empty");
            return;
        }
        if(!confirm.equals(password)) {
            confirmInput.setText("");
            confirmInput.setHint("Doesn't match.");
            return;
        }
        //TODO check with database

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Signup", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            onAuthSuccess(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Signup", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }


    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());


        // Go to MainActivity
        startActivity(new Intent(Signup.this, login.class));
        finish();
    }

    private void writeNewUser(String userId, String name, String email) {
        UserInfo user = new UserInfo();
        user.setUserName(name);
        user.setEmail(email);
        user.setGender(Gender.UNKNOWN);
        user.setAge(0);
        Map<String, Object> userValue = user.toMap();

        db.child("Users").child(userId).setValue(userValue);
    }

}
