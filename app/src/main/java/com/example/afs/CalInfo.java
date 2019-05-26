package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CalInfo extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_info);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.calInfoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {back();
            }
        });
    }

    private void back() {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }
}
