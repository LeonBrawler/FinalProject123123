package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class FlashlightActivity extends AppCompatActivity {

    boolean hasCameraFlash= false;
    boolean flashOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);
        ImageView toggleButton = findViewById(R.id.toggle_flashlight);
        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(hasCameraFlash){
                    try {
                        toggleFlashlight(flashOn);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                    flashOn = !flashOn;
                } else {
                    Toast.makeText(FlashlightActivity.this, "No flashlight available on your device", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @SuppressLint("NewApi")
    private void toggleFlashlight(boolean flashOn) throws CameraAccessException{
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String camId = cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(camId, flashOn);
        //cameraManager.
    }


}
