package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
    private Button loginButton;
    private TextView signupText;
    private EditText emailInput;
    private EditText passwordInput;


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

        signupText = (TextView) findViewById(R.id.sign_up_text);
        signupText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signIn() {

        emailInput = (EditText) findViewById(R.id.Email);
        passwordInput = (EditText) findViewById(R.id.Password);
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        //TODO needs to confirm with database to sign in


        Intent intent = new Intent(this, WorkOut.class);
        startActivity(intent);
    }

    private void signUp() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

}
