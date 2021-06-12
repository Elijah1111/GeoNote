package com.trimble.ag.splice.geonote.GeoNoteMap;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.trimble.ag.splice.Extension;
import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.R;
import com.trimble.ag.splice.geonote.databinding.GeonoteBinding;
import com.trimble.ag.toolkit.ui.SpliceFragment;

import java.util.Collections;
import java.util.List;

public class GeoNoteFragment extends SpliceFragment{
    private static final String TAG = "GeoNoteFragment";

    public GeoNoteFragment() {
        // Required empty public constructor
    }

    public GeoNoteFragment(Extension extension) {
        super(extension);
    }

    protected MapView mMapView;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager layoutManager;
    protected GeoNoteAdapter mAdapter;
    private GeoNoteFragmentViewModel geoNoteFragmentViewModel;
    private GoogleMap googleMap;

    private void updateUI(List<GeoNote> geoNotes){
        mAdapter = new GeoNoteAdapter(geoNotes);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        GeoNoteFragmentViewModelFactory factory = new GeoNoteFragmentViewModelFactory(requireContext());
        geoNoteFragmentViewModel = new ViewModelProvider(this, factory).get(GeoNoteFragmentViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = getLayoutInflater(inflater).inflate(R.layout.geonote, container, false);
        mMapView =(MapView) rootView.findViewById(R.id.mapview);
        mRecyclerView=(RecyclerView) rootView.findViewById(R.id.your_geonote_list_recycler_view);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        updateUI(Collections.emptyList());
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        geoNoteFragmentViewModel.geoNoteLiveData.observe(
                getViewLifecycleOwner(), geoNotes -> {
                    Log.i(TAG, "Got GeoNotes "+geoNotes.size());
                    updateUI(geoNotes);
                }
                );
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}


    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getLayoutInflater(inflater).inflate(R.layout.geonote, container, false);
    }*/
