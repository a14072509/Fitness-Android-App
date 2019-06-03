package com.example.afs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AerobicToCal extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_aerobic);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.aerobicToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Aerobic Exercise");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
