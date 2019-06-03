package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class login extends firebaseActivity {
    private Button loginButton;
    private TextView forgetButton;
    private TextView signupText;
    private TextView forgetPasswordText;
    private EditText emailInput;
    private EditText passwordInput;
    private DatabaseReference db;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginButton = (Button) findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signIn();
            }
        });

        forgetButton = (TextView) findViewById(R.id.forget_password_text);
        forgetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { resetPassword();
            }
        });

        signupText = (TextView) findViewById(R.id.sign_up_text);
        signupText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signUp();
            }
        });

        //initialize database
        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart()
    {
        super.onStart();

        if(mAuth.getCurrentUser() != null)
        {
            FirebaseUser curUser = mAuth.getCurrentUser();
            Intent intent = new Intent(this, WorkOut.class);
            startActivity(intent);
        }
    }

    private void signIn() {

        emailInput = (EditText) findViewById(R.id.Email);
        passwordInput = (EditText) findViewById(R.id.Password);
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        //TODO needs to confirm with database to sign in
        if(!validateForm()) return;
        showProgressDialog();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                Log.d("login", "signIn:onComplete:" + task.isSuccessful());
                hideProgressDialog();

                if(!task.isSuccessful())
                {
                    Toast.makeText(login.this, "Sign In Failed",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, login.class);
                    startActivity(intent);
                }

                else {
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void signUp() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    private void resetPassword() {
        Intent intent = new Intent(this, ForgetPassword.class);
        startActivity(intent);
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(emailInput.getText().toString())) {
            emailInput.setError("Required");
            result = false;
        } else {
            emailInput.setError(null);
        }

        if (TextUtils.isEmpty(passwordInput.getText().toString())) {
            passwordInput.setError("Required");
            result = false;
        } else {
            passwordInput.setError(null);
        }

        return result;
    }


}
