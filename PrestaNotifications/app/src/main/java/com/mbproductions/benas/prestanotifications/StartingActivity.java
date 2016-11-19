package com.mbproductions.benas.prestanotifications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Benas on 9/27/2016.
 */
public class StartingActivity extends AppCompatActivity {

    private ImageView heart_IV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        heart_IV = (ImageView) findViewById(R.id.bell);
        Animation pulse = AnimationUtils.loadAnimation(StartingActivity.this, R.anim.shake);
        heart_IV.startAnimation(pulse);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(StartingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 1800L);
    }
}
