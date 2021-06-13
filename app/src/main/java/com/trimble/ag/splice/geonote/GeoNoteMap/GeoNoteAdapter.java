package com.trimble.ag.splice.geonote.GeoNoteMap;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.R;
import com.trimble.ag.splice.geonote.databinding.GeonoteListItemBinding;

import java.util.List;

public class GeoNoteAdapter extends RecyclerView.Adapter<GeoNoteHolder> {
    private List<GeoNote> mGeoNotes;
    public GeoNoteAdapter(List<GeoNote> geoNotes) {
        mGeoNotes = geoNotes;
    }

    @Override
    public GeoNoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.geonote_list_item, parent, false);
        /*GeonoteListItemBinding binding = GeonoteListItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);*/
        return new GeoNoteHolder(v);
    }

    @Override
    public void onBindViewHolder(GeoNoteHolder holder, int position) {
        GeoNote current = mGeoNotes.get(position);
        holder.bind(current);
    }

    @Override
    public int getItemCount() {
        return mGeoNotes.size();
    }

/*    static class GeoNoteDiff extends DiffUtil.ItemCallback<GeoNote> {

        @Override
        public boolean areItemsTheSame(@NonNull GeoNote oldItem, @NonNull GeoNote newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull GeoNote oldItem, @NonNull GeoNote newItem) {
            return oldItem.uid.equals(newItem.uid);
        }
    }*/
}