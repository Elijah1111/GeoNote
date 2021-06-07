package com.trimble.ag.splice.geonote;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.trimble.ag.splice.Extension;
import com.trimble.ag.toolkit.ui.SpliceFragment;

public class GeoNoteFragment extends SpliceFragment implements OnMapReadyCallback{
    private static final String TAG = "GeoNoteFragment";

    public GeoNoteFragment() {
        // Required empty public constructor
    }

    public GeoNoteFragment(Extension extension) {
        super(extension);
    }


    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= getLayoutInflater(inflater).inflate(R.layout.geonote, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.main_branch_map);
        mapFragment.getMapAsync(this);
        return view;
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng UCA = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(UCA).title("YOUR TITLE")).showInfoWindow();

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UCA,17));

    }
}


    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getLayoutInflater(inflater).inflate(R.layout.geonote, container, false);
    }*/
