package com.example.afs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Signup extends AppCompatActivity {


    private EditText emailInput;
    private EditText confirmInput;
    private EditText passwordInput;
    private Button signUpButton;
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
    }


}
