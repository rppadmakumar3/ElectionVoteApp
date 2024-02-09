package com.example.impactmakers.electionvote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Animatable;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_Activity.this,OtpSendActivity.class));
                finish();

            }
        },3000);
    }

}