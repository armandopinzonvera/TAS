package com.tracking.tas.Fragments;
/*
* El metodo onCreate se genero por override
*
*
* */

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.tracking.tas.Interface;
import com.tracking.tas.Presenter;
import com.tracking.tas.R;

import org.json.JSONObject;

import java.util.concurrent.Executor;

public class FragMapa extends Fragment implements OnMapReadyCallback, Interface.Model {


    public String lat1, lon1;
    private GoogleMap mMap;
    private double result;


    public static final int DEFAULT_UPDATE_INTERVAL = 30;
    public static final int FAST_UPDATE_INTERVAL = 30;
    @SuppressLint("RestrictedApi")
    LocationRequest locationRequest = new LocationRequest();
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int PERMISSIONS_FINE_LOCATION = 99;

    public float longitudOrigen, latitudOrigen;
    public float lantitu, longitu;
    Boolean actualPosition= true;
    JSONObject jso;
    private Interface.Presenter presenter;

    ///////////////////////////////////////////////////////////////
    //  constructor

    public FragMapa() {
        // Required empty public constructor
    }


    //////////////////////////////////////////

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationRequest.setInterval(1000 * DEFAULT_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(1000 * FAST_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        updateGPS();
        locationRequest = new LocationRequest();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case PERMISSIONS_FINE_LOCATION:
                if (grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    updateGPS();
                }else{
                    Toast.makeText(getActivity(), "Esta aplicacion requiere permisos", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    private void updateGPS() {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(getActivity(),
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            updateUIValues(location);
                        }
                    });
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    private void updateUIValues(Location location) {
        latitudOrigen = (float) location.getLatitude();
       longitudOrigen = (float) location.getLongitude();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_mapa, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_name);
        mapFragment.getMapAsync(this);
        return view;
    }
   public FragMapa(Interface.Presenter presenter){
        this.presenter = presenter;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        updateGPS();

        LatLng miPosicion = new LatLng(latitudOrigen, longitudOrigen);
        mMap.addMarker(new MarkerOptions().position(miPosicion).title("Mi Ubicacion"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(miPosicion));

    }

    ///////////////////////////////////////////////////////
    /////////////////////////////////////////////////////
    public void xxcalculus(String data){
        result = Double.valueOf(data) * 5.56;
        presenter.xxshowResultPresenter(String.valueOf(result));


    }

}
