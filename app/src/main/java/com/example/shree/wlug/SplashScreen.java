package com.example.shree.wlug;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 6000;

    ImageView image;
    TextView wlug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image= (ImageView) findViewById(R.id.image);
        wlug= (TextView) findViewById(R.id.wlug);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sequential);
        wlug.startAnimation(animation);

        Animation animation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        image.startAnimation(animation1);

        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            },SPLASH_TIME_OUT);

    }
}
