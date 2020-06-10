package com.tracking.tas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.tracking.tas.Fragments.FragMapa;

import java.util.concurrent.Executor;

public class Ubicaxion extends AppCompatActivity {


    public static final int DEFAULT_UPDATE_INTERVAL = 30;
    public static final int FAST_UPDATE_INTERVAL = 30;
    @SuppressLint("RestrictedApi")
    LocationRequest locationRequest = new LocationRequest();
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int PERMISSIONS_FINE_LOCATION = 99;

    float longitudOrigen, latitudOrigen;
    Boolean actualPosition= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationRequest.setInterval(1000 * DEFAULT_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(1000 * FAST_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        updateGPS();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case PERMISSIONS_FINE_LOCATION:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    updateGPS();
                }else{
                    Toast.makeText(this, "Esta aplicacion requiere permisos", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }
    private void updateGPS() {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener((Executor) this,
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            
                            updateUIValues(location);
                        }
                    });
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, PERMISSIONS_FINE_LOCATION);
            }
        }
    }

    private void updateUIValues(Location location) {
        latitudOrigen = (float) location.getLatitude();
        longitudOrigen= (float)location.getLongitude();

        FragMapa fragMapa = new FragMapa();
        Bundle bundle1 = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle1.putFloat("key1", latitudOrigen);
        bundle2.putFloat("key2", longitudOrigen);
        fragMapa.setArguments(bundle1);
        fragMapa.setArguments(bundle2);

        Intent intent = new Intent(this, ActivityIniciar.class);
        Bundle bundle3 = new Bundle();
        bundle3.putFloat("key3", latitudOrigen);
        intent.putExtras(bundle3);
    }


}
