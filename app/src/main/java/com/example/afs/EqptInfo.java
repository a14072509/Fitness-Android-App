package com.example.afs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class EqptInfo extends AppCompatActivity {

    private Button addExerButton;
    private TextView title;
    VideoView videoView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment_info);

        addExerButton = (Button)findViewById(R.id.add_exercise_button);
        addExerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addItem();
            }
        });

        String instructions = getIntent().getStringExtra("instruction");
        TextView instrText = (TextView)findViewById(R.id.instruction_view);

        instrText.setText(instructions);
        videoView = (VideoView)findViewById(R.id.exer_gif);
        String name = getIntent().getStringExtra("image");
        Resources res = this.getResources();
        int resID = res.getIdentifier(name , "raw", this.getPackageName());
        Uri uri= Uri.parse("android.resource://" + getPackageName() + "/" + resID);

        videoView.setVideoURI(uri);
        videoView.start();

        //Video Loop
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                videoView.start(); //need to make transition seamless.
            }
        });

        videoView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                if (videoView.isPlaying()) {
                    videoView.pause();
                } else {
                    videoView.start();
                }
                return false;
            }
        });

        title = (TextView)findViewById(R.id.equipmentTitle);
        title.setText(getIntent().getStringExtra("name"));
    }

    private void addItem()
    {
        //TODO
        Intent intent = new Intent(this, ExeToCal.class);
        intent.putExtra("image", getIntent().getStringExtra("image"));
        intent.putExtra("name", getIntent().getStringExtra("name"));
        startActivity(intent);
    }

}
