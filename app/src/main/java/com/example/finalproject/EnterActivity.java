package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
        Button toFlashlightBtn;
        Button toCompassBtn;
        Button toCovidInfoBtn;


        toWeatherBtn = findViewById(R.id.to_weather_btn);
        toWeatherBtn.setOnClickListener(this);

        toTipsBtn = findViewById(R.id.to_tips_btn);
        toTipsBtn.setOnClickListener(this);

        toMapsBtn = findViewById(R.id.to_maps_btn);
        toMapsBtn.setOnClickListener(this);

        toFlashlightBtn = findViewById(R.id.to_flashlight_btn);
        toFlashlightBtn.setOnClickListener(this);

        toCompassBtn = findViewById(R.id.to_compass_btn);
        toCompassBtn.setOnClickListener(this);

        toCovidInfoBtn = findViewById(R.id.to_covid_info);
        toCovidInfoBtn.setOnClickListener(this);
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
            case R.id.to_flashlight_btn:
                goToFlashlight();
                break;
            case R.id.to_compass_btn:
                goToCompass();
                break;
            case R.id.to_covid_info:
                goToCovidInfo();
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

    private void goToFlashlight(){
        switchActivityIntent = new Intent(this, FlashlightActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goToCompass(){
        switchActivityIntent = new Intent(this, CompassActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goToCovidInfo(){
        switchActivityIntent = new Intent(this, CovidActivity.class);
        startActivity(switchActivityIntent);
    }
}