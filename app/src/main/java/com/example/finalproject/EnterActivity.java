package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        Button toWeatherBtn;
        Button toTipsBtn;
        toWeatherBtn = findViewById(R.id.toWeatherBtn);
        toWeatherBtn.setOnClickListener(this);
        toTipsBtn = findViewById(R.id.toTipsBtn);
        toTipsBtn.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.toWeatherBtn:
                goToWeather();
                break;
            case R.id.toTipsBtn:
                gotoTips();
                break;
        }
    }

    private void goToWeather(){
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void gotoTips(){
        Intent switchActivityIntent1 = new Intent(this, TipsActivity.class);
        startActivity(switchActivityIntent1);
    }
}