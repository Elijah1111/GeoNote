package com.trimble.ag.splice.geonote;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.trimble.ag.splice.Extension;
import com.trimble.ag.toolkit.ui.SpliceFragment;

public class GeoNoteFragment extends SpliceFragment {

    public GeoNoteFragment() {
        // Required empty public constructor
    }

    public GeoNoteFragment(Extension extension) {
        super(extension);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getLayoutInflater(inflater).inflate(R.layout.geonote, container, false);
    }
}
