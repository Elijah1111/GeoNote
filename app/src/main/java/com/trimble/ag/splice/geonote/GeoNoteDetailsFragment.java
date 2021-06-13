package com.trimble.ag.splice.geonote;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trimble.ag.splice.Extension;
import com.trimble.ag.toolkit.ui.SpliceFragment;

public class GeoNoteDetailsFragment extends SpliceFragment {
    private static final String TAG = "GeonoteDetailFragment";
    public GeoNoteDetailsFragment() {
        // Required empty public constructor
    }

    public GeoNoteDetailsFragment(Extension extension) {
        super(extension);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "On create");
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return getLayoutInflater(inflater).inflate(R.layout.fragment_geonote_details, container, false);
    }
}
