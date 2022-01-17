package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class TipsActivity extends AppCompatActivity implements View.OnClickListener {
    public TextView textView = new TextView(this);
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Button buttonEquipment = findViewById(R.id.button_equipment);
        Button buttonMapUsing = findViewById(R.id.map_using);
        Button buttonTents = findViewById(R.id.tents);
        Button buttonMushrooms = findViewById(R.id.mushrooms);
        Button buttonBonfire = findViewById(R.id.bonfire);
        scrollView = findViewById(R.id.scrollView);
    }


    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_equipment:
                textView.setText("Text about equipment");
                break;
            case R.id.map_using:
                textView.setText("Text about maps and using them");
                break;
            case R.id.tents:
                textView.setText("Text about tents");
                break;
            case R.id.mushrooms:
                textView.setText("Text about mushrooms");
                break;
            case R.id.bonfire:
                textView.setText("Text about bonfire");
                break;
        }
        scrollView.addView(textView);
    }
}