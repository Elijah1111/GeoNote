package com.trimble.ag.splice.geonote.GeoNoteMap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.R;

import java.util.List;

public class GeoNoteDetailsAdapter extends RecyclerView.Adapter<GeoNoteDetailsHolder> {
    private GeoNote mGeoNote;
    private GeoNoteFragmentViewModel geoNoteFragmentViewModel;

    public GeoNoteDetailsAdapter(GeoNote geoNote) {
        mGeoNote = geoNote;
    }

    @Override
    public GeoNoteDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_geonote_details, parent, false);
        /*GeonoteListItemBinding binding = GeonoteListItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);*/
        return new GeoNoteDetailsHolder(v, geoNoteFragmentViewModel);
    }

    @Override
    public void onBindViewHolder(GeoNoteDetailsHolder holder, int position) {
        GeoNote current = mGeoNote;
        holder.bind(current);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void addViewModel(GeoNoteFragmentViewModel geoNoteFragmentViewModel) {
        this.geoNoteFragmentViewModel = geoNoteFragmentViewModel;
    }
}