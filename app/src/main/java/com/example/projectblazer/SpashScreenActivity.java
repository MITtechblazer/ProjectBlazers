package com.example.projectblazer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SpashScreenActivity extends AppCompatActivity {
    private static int Limit=2000;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_spash_screen);

        getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_on_primary));
        //will delay splash page and then go to main screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =  new Intent(SpashScreenActivity.this,WelcomeActivity.class);
                startActivity(intent);
                finish();

            }
        }, Limit);
    }
}