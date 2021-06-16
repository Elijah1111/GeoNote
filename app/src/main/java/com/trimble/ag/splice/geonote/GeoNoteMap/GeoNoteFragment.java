package com.trimble.ag.splice.geonote.GeoNoteMap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
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
    private Boolean redirect = false;
    private Boolean loaded = true;
    private List<GeoNote> loadLater;//load these geoNotes when ready

    @SuppressLint("ValidFragment")
    public GeoNoteFragment(Extension extension){
        super(extension);
        urlPrefix = "http://localhost:8081/com.trimble.ag.splice.geonote.GeoNoteExtension/";
    }


    protected static List<GeoNote> geoNotes;
    protected static RecyclerView mRecyclerView;
    protected LinearLayoutManager layoutManager;
    protected static GeoNoteAdapter mAdapter;
    private static GeoNoteFragmentViewModel geoNoteFragmentViewModel;

    public static void clickedGeonote(GeoNote geoNote) {//When a GeoNote is clicked it redirects to the details
        GeoNoteDetailsAdapter detailsAdapter = new GeoNoteDetailsAdapter(geoNote);
        detailsAdapter.addViewModel(geoNoteFragmentViewModel);
        mRecyclerView.setAdapter(detailsAdapter);
    }

    public static void backToList() {
        updateUI(geoNotes);
    }//Updates the view when the user navigates back

    private static void updateUI(List<GeoNote> geoNotes){//Sets the adapter to the current list of GeoNotes
        Log.d(TAG, "Update with "+ geoNotes.size());
        mAdapter = new GeoNoteAdapter(geoNotes);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {//Creates the instance of the fragment and view model
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

    @SuppressLint("SetJavaScriptEnabled")//Ignore the issue, should be no problem on tablet
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called");
        View rootView = getLayoutInflater(inflater).inflate(R.layout.geonote, container, false);//Sets the root view
        webView = rootView.findViewById(R.id.webView);

        mRecyclerView= rootView.findViewById(R.id.your_geonote_list_recycler_view);//sets the recycler view


        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);


        if(webView != null) {//Sets up interface to Map webpage
            if(webViewClient == null) {
                webViewClient = new WebViewClient(){

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        if(!loaded){
                            redirect = true;
                        }
                        loaded = false;
                        webView.loadUrl(url);
                        return true;
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        loaded = false;
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        if(!redirect) {
                            loaded = true;
                        }
                        if(loaded && !redirect){
                            Log.d(TAG,"Web page Completely Loaded: got "+loadLater.size()+" GeoNotes to draw!");

                            for (GeoNote note : loadLater) {//load the geonotes if they are not loaded yet
                                double[] pos = note.getPos();
                                String eval = "javascript:addGeoNote('"+note.getName()+" '," + pos[1] + "," + pos[0] + "," + note.getPictures() + ")";
                                Log.d("aea3", eval);
                                evaluateJavascript(eval);
                            }
                        }
                        else{
                            redirect = false;
                        }
                    }
                };
            }
            webView.setWebViewClient(webViewClient);
            webView.getSettings().setJavaScriptEnabled(true);
            if(webViewBundle == null) {//sets the url for the webpage
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
        geoNoteFragmentViewModel.getGeoNoteLiveData().observe(//Observes the live data and updates the list and map with the information
                getViewLifecycleOwner(), geoNotes -> {
                    Log.i(TAG, "Got GeoNotes " + geoNotes.size());
                    GeoNoteFragment.geoNotes = geoNotes;
                    updateUI(geoNotes);
                    if(loaded && !redirect) {
                        for (GeoNote note : geoNotes) {
                            double[] pos = note.getPos();
                            String eval = "javascript:addGeoNote(" + note.getName() + "," + pos[1] + "," + pos[0] + "," + note.getPictures() + ")";
                            Log.d("aea3", eval);
                            evaluateJavascript(eval);
                        }
                    }
                    else{//The web page is not done loading yet load when done
                        loadLater = geoNotes;
                    }
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
