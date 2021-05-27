package com.trimble.ag.splice.geonote.GeoNoteDrawer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.trimble.ag.splice.Extension;
import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.GeoNoteDrawer.GeonoteDrawerAdapter;
import com.trimble.ag.splice.geonote.GeoNoteType;
import com.trimble.ag.splice.geonote.R;
import com.trimble.ag.toolkit.ui.SpliceFragment;

public class GeoNoteDrawerFragment extends SpliceFragment {
    private static final String TAG = "GeonoteDrawerFragment";
    protected RecyclerView recyclerView;
    protected GridLayoutManager layoutManager;
    protected GeonoteDrawerAdapter adapter;
    protected String[] Dataset;
    protected int[] Imageset;
    private GeoNoteDrawerViewModel geoNoteDrawerViewModel;
    public GeoNoteDrawerFragment() {
        // Required empty public constructor
    }

    public GeoNoteDrawerFragment(Extension extension) {
        super(extension);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "On create");
        super.onCreate(savedInstanceState);
        initItemset();
        adapter = new GeonoteDrawerAdapter(Dataset,Imageset);
        geoNoteDrawerViewModel = new ViewModelProvider(this).get(GeoNoteDrawerViewModel.class);
    }

    public void initItemset() {
        Log.d(TAG, "Itemset");
        Dataset = new String[]{"Crop","Garbage","Generic","Hazard","Livestock","Pest","Product","Spill","Weed"};
        Imageset = new int[]{R.drawable.crop,R.drawable.garbage,R.drawable.generic,R.drawable.hazard,R.drawable.livestock,R.drawable.pest, R.drawable.product, R.drawable.spill, R.drawable.weed};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "On create view ");
        View root = getLayoutInflater(inflater).inflate(R.layout.geonote_drawer, container, false);

        // BEGIN_INCLUDE(initializeRecyclerView)
        recyclerView = (RecyclerView) root.findViewById(R.id.your_geonote_list_recycler_view);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.

        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(adapter);

        return (root);
        // Inflate the layout for this fragment

        //return getLayoutInflater(inflater).inflate(R.layout.geonote_drawer, container, false);
    }
    public void addGeoNote(Drawable drawable, String name){
        GeoNoteType geoNoteType = null;
        if(name == "Crop"){
            geoNoteType = GeoNoteType.CROP;
        }
        if(name == "Garbage"){
            geoNoteType = GeoNoteType.TRASH;
        }
        if(name == "Livestock"){
            geoNoteType = GeoNoteType.ANIMAL;
        }
        if(name == "Pest"){
            geoNoteType = GeoNoteType.PEST;
        }
        if(name == "Product"){
            geoNoteType = GeoNoteType.PRODUCT;
        }
        if(name == "Spill"){
            geoNoteType = GeoNoteType.SPILL;
        }
        if(name =="Weed"){
            geoNoteType = GeoNoteType.WEED;
        }
        if(name =="Hazard"){
            geoNoteType = GeoNoteType.HAZARD;
        }
        GeoNote geoNote =new GeoNote(name, drawable, geoNoteType);
        geoNoteDrawerViewModel.insert(geoNote);
    }
}
