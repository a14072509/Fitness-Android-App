package com.example.afs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EqptInfo extends AppCompatActivity {

    private Button addExerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eqpt_info);

        addExerButton = (Button)findViewById(R.id.add_exercise_button);
        addExerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addItem();
            }
        });

        String instructions = getIntent().getStringExtra("instruction");
        TextView instrText = (TextView)findViewById(R.id.instruction_view);

        instrText.setText(instructions);

    }

    private void addItem()
    {
        //TODO
    }


}
