package com.trimble.ag.splice.geonote.GeoNoteMap;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trimble.ag.splice.Extension;
import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.R;
import com.trimble.ag.toolkit.ui.SpliceFragment;

import java.util.Collections;
import java.util.List;

public class GeoNoteFragment extends SpliceFragment{
    private static final String TAG = "GeoNoteFragment";

    public GeoNoteFragment() {
        // Required empty public constructor
    }
    private WebViewClient webViewClient;
    private WebView webView;
    private Bundle webViewBundle;
    private String urlPrefix = "file:///android_asset/";

    @SuppressLint("ValidFragment")
    public GeoNoteFragment(Extension extension){
        super(extension);
        urlPrefix = "http://localhost:8081/com.trimble.ag.splice.geonote.GeoNoteExtension/";
    }


    //protected MapView mMapView;
    protected static List<GeoNote> geoNotes;
    protected static RecyclerView mRecyclerView;
    protected LinearLayoutManager layoutManager;
    protected static GeoNoteAdapter mAdapter;
    private static GeoNoteDetailsAdapter detailsAdapter;
    private static GeoNoteFragmentViewModel geoNoteFragmentViewModel;

    public static void clickedGeonote(GeoNote geoNote) {
        detailsAdapter = new GeoNoteDetailsAdapter(geoNote);
        detailsAdapter.addViewModel(geoNoteFragmentViewModel);
        mRecyclerView.setAdapter(detailsAdapter);
    }

    public static void backToList() {
        updateUI(geoNotes);
    }
    // private GoogleMap googleMap;

    private static void updateUI(List<GeoNote> geoNotes){
        Log.d(TAG, "Update with "+ geoNotes.size());
        mAdapter = new GeoNoteAdapter(geoNotes);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        GeoNoteFragmentViewModelFactory factory = new GeoNoteFragmentViewModelFactory(getActivity());
        geoNoteFragmentViewModel = new ViewModelProvider(this, factory).get(GeoNoteFragmentViewModel.class);

    }

    @Override
    public void onPause() {
        super.onPause();
        webViewBundle = new Bundle();
        webView.saveState(webViewBundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called");
        View rootView = getLayoutInflater(inflater).inflate(R.layout.geonote, container, false);
        webView =(WebView) rootView.findViewById(R.id.webView);
        mRecyclerView=(RecyclerView) rootView.findViewById(R.id.your_geonote_list_recycler_view);


        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);


        if(webView != null) {
            if(webViewClient == null) {
                webViewClient = new WebViewClient();
            }
            webView.setWebViewClient(webViewClient);
            webView.getSettings().setJavaScriptEnabled(true);
            if(webViewBundle == null) {
                String url = urlPrefix+"www/geonote_map.html";
                Log.d("aea", "loading webview from url: "+url);
                webView.loadUrl(url);
            }
            else {
                webView.restoreState(webViewBundle);
            }
        }

        updateUI(Collections.emptyList());
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated() called");
        geoNoteFragmentViewModel.getGeoNoteLiveData().observe(
                getViewLifecycleOwner(), geoNotes -> {
                    Log.i(TAG, "Got GeoNotes " + geoNotes.size());
                    this.geoNotes = geoNotes;
                    updateUI(geoNotes);
                }
        );
    }
    public void evaluateJavascript(String js) {
        webView.evaluateJavascript(js, null);
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }
}


    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getLayoutInflater(inflater).inflate(R.layout.geonote, container, false);
    }*/
