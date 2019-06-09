package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    private EditText email;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        reset = (Button)findViewById(R.id.reset_password_button);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendPasswordReset();
            }
        });

    }

    private void sendPasswordReset() {
        // [START send_password_reset]
        FirebaseAuth auth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.email);
        String emailAddress = email.getText().toString();

        if (TextUtils.isEmpty(emailAddress)) {
            email.setError("Required");
            return;
        } else {
            email.setError(null);
        }

        int index = email.getText().toString().indexOf("@");

        if (index == -1 || index == 0 || index == email.getText().toString().length()-1) {
            email.setError("Invalid");
            return;
        } else {
            email.setError(null);
        }

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("login", "Email sent.");
                        }
                    }
                });
        // [END send_password_reset]
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}
