package com.example.projectblazer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome);


    }
    public void goToLogin(View view) {
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(WelcomeActivity.this,SignupActivity.class);
        startActivity(intent);
    }
}