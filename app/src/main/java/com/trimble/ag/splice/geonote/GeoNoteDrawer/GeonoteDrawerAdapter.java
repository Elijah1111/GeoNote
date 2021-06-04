package com.trimble.ag.splice.geonote.GeoNoteDrawer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.navigation.NavDirections;


import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.trimble.ag.splice.geonote.Database.GeoNoteRepository;
import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.GeoNoteType;
import com.trimble.ag.splice.geonote.R;

import java.util.Objects;
import java.util.zip.Inflater;

public class GeonoteDrawerAdapter extends RecyclerView.Adapter<GeonoteDrawerAdapter.ViewHolder>{

    private static final String TAG = "DrawerAdapter";
    private String[] mDataSet;
    private int[] mImageSet;
    private GeoNoteRepository repository;

    public void addRepo(GeoNoteRepository mRepository) {
        repository = mRepository;
    }


    // private GeoNoteDrawerFragment geoNoteDrawerFragment;
    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        private GeoNoteRepository repository;
        public ViewHolder(View v, GeoNoteRepository mRepository, int[] images, String[] data) {
            super(v);
            repository =mRepository;
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    addGeoNote(images[getAdapterPosition()], data[getAdapterPosition()]);
                   // geoNoteDrawerFragment.addGeoNote(image[getAdapterPosition()], data[getAdapterPosition()]);
                }
            });
            textView = (TextView) v.findViewById(R.id.note_name_text_view);
            imageView = (ImageView) v.findViewById(R.id.note_image);
        }

        public TextView getTextView() {
            return textView;
        }
        public ImageView getImageView() {
            return imageView;
        }

        public void addGeoNote(int drawable, String name){
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
            GeoNote geoNote =new GeoNote();
            geoNote.name = name;
            geoNote.type = geoNoteType;
            geoNote.icon =drawable;
            // geoNoteDrawerViewModel.insert(geoNote);
            repository.addGeoNote(geoNote);

        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    public GeonoteDrawerAdapter(String[] dataSet, int[] imageSet){
        mDataSet = dataSet;
        mImageSet = imageSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "On create view holder");
        // Create a new view.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.geonote_drawer_item, parent, false);
       // geoNoteDrawerFragment = new GeoNoteDrawerFragment();
        return new ViewHolder(v,repository,mImageSet, mDataSet);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        holder.getTextView().setText(mDataSet[position]);
        holder.getImageView().setImageResource(mImageSet[position]);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "On item count");
        return mDataSet.length;
    }


}
