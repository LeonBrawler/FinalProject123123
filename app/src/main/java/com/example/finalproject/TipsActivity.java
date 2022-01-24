package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class TipsActivity extends AppCompatActivity implements View.OnClickListener {
    public TextView textView;
    public TextView tipsText;
    public String tip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Button buttonEquipment = findViewById(R.id.button_equipment);
        Button buttonMapUsing = findViewById(R.id.map_using);
        Button buttonTents = findViewById(R.id.tents);
        Button buttonMushrooms = findViewById(R.id.mushrooms);
        Button buttonBonfire = findViewById(R.id.bonfire);
        buttonEquipment.setOnClickListener(this);
        buttonMapUsing.setOnClickListener(this);
        buttonTents.setOnClickListener(this);
        buttonMushrooms.setOnClickListener(this);
        buttonBonfire.setOnClickListener(this);
        TextView tipsText = findViewById(R.id.tipsView);

    }


    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_equipment:
                Toast toast = Toast.makeText(getApplicationContext(), "Equipment", Toast.LENGTH_LONG);
                toast.show();
                break;
            case R.id.map_using:
                Toast toast1 = Toast.makeText(getApplicationContext(), "maps", Toast.LENGTH_LONG);
                toast1.show();
                break;
            case R.id.tents:
                Toast toast2 = Toast.makeText(getApplicationContext(), "tents", Toast.LENGTH_LONG);
                toast2.show();
                break;
            case R.id.mushrooms:
                Toast toast3 = Toast.makeText(getApplicationContext(), "mushrooms", Toast.LENGTH_LONG);
                toast3.show();
                break;
            case R.id.bonfire:
                Toast toast4 = Toast.makeText(getApplicationContext(), "bonfire", Toast.LENGTH_LONG);
                toast4.show();
                break;
        }

    }
}