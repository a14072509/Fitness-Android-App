package com.example.afs;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ExeToCal extends AppCompatActivity {

    private ImageView image;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise);
        String name = getIntent().getStringExtra("image") + "_1";
        Resources res = this.getResources();
        int resID = res.getIdentifier(name , "drawable", this.getPackageName());
        Drawable drawable = res.getDrawable(resID );
        image = (ImageView)findViewById(R.id.add_exercise_image);
        image.setImageDrawable(drawable );

        addButton = (Button)findViewById(R.id.exercise_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                add();
            }
        });
    }

    private void add()
    {
        //TODO add the information to database


        finish();
    }
}
