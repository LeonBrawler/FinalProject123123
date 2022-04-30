package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        Button toWeatherBtn;
        Button toTipsBtn;
        Button toMapsBtn;
        Button toCompassBtn;

        toWeatherBtn = findViewById(R.id.to_weather_btn);
        toWeatherBtn.setOnClickListener(this);

        toTipsBtn = findViewById(R.id.to_tips_btn);
        toTipsBtn.setOnClickListener(this);

        toMapsBtn = findViewById(R.id.to_maps_btn);
        toMapsBtn.setOnClickListener(this);

        toCompassBtn = findViewById(R.id.to_game_btn);
        toCompassBtn.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.to_weather_btn:
                goToWeather();
                break;
            case R.id.to_tips_btn:
                goToTips();
                break;
            case R.id.to_maps_btn:
                goToMaps();
                break;
            case R.id.to_game_btn:
                goToGame();
                break;
        }
    }
    Intent switchActivityIntent;
    private void goToWeather(){
        switchActivityIntent = new Intent(this, WeatherActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goToTips(){
        switchActivityIntent = new Intent(this, TipsActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goToMaps(){
        switchActivityIntent = new Intent(this, MapsActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goToGame(){
        switchActivityIntent = new Intent(this, GameActivity.class);
        startActivity(switchActivityIntent);
    }
}