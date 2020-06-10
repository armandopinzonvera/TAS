package com.tracking.tas.Fragments;
/*
* El metodo onCreate se genero por override
*
*
* */

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tracking.tas.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragMapa extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    float lat2, long2;

    public FragMapa() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            lat2 = getArguments().getFloat("key1");
            long2= getArguments().getFloat("key2");
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_mapa, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_name);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        LatLng miPosicion = new LatLng(lat2, long2);
        mMap.addMarker(new MarkerOptions().position(miPosicion).title("Mi Ubicacion"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(miPosicion));


    }
}
