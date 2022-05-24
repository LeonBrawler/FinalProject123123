package com.example.finalproject;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.MapsInitializer.Renderer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnMapsSdkInitializedCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, OnMapsSdkInitializedCallback {

    private GoogleMap mMap;
    static DBManager dbManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        dbManager = DBManager.getInstance(this);

        MapsInitializer.initialize(getApplicationContext(), Renderer.LATEST, this); // if not working -> delete this line
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<Result> markers = dbManager.getAllMarkerData();
        for(int i = 0; i < markers.size(); ++i){
            MarkerOptions marker = new MarkerOptions().
                    position(new LatLng(Double.parseDouble(markers.get(i).posX), Double.parseDouble(markers.get(i).posY))).
                    title("New Marker").
                    draggable(true);
            googleMap.addMarker(marker);
        }
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng point) {
                MarkerOptions marker = new MarkerOptions().
                        position(new LatLng(point.latitude, point.longitude)).
                        title("New Marker").
                        draggable(true);
                googleMap.addMarker(marker);
                dbManager.addMarkerToDb(point.latitude+"", point.longitude+"");
                //add to db with strings: point.latitude, point.longitude
            }
        });
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                LatLng markerPos = marker.getPosition();
                Double longitude = markerPos.longitude;
                Double latitude = markerPos.latitude;
                dbManager.deleteMarkerFromDb(latitude+"", longitude+"");
                marker.remove();
                return false;
            }
        });
    }


    @Override
    public void onMapsSdkInitialized(@NonNull MapsInitializer.Renderer renderer) {
        switch (renderer) {
            case LATEST:
                Log.d("MapsDemo", "The latest version of the renderer is used.");
                break;
            case LEGACY:
                Log.d("MapsDemo", "The legacy version of the renderer is used.");
                break;
        }
    }
}
