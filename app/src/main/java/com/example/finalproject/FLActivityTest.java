package com.example.finalproject;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.hardware.lights.Light;
import android.hardware.lights.LightsManager;
import android.hardware.lights.LightsRequest;
import android.hardware.lights.LightState;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


@RequiresApi(api = Build.VERSION_CODES.S)
public class FLActivityTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fl_activity_test);
    }





}
