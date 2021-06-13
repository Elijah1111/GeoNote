package com.trimble.ag.splice.geonote.GeoNoteDrawer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.trimble.ag.splice.Extension;
import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.GeoNoteExtension;
import com.trimble.ag.splice.geonote.GeoNoteType;
import com.trimble.ag.splice.geonote.R;
import com.trimble.ag.splice.location.Location;

public class GeonoteDrawerAdapter extends RecyclerView.Adapter<GeonoteDrawerAdapter.ViewHolder>{

    private static final String TAG = "GeoNoteDrawerAdapter";
    private final String[] mDataSet;
    private final int[] mImageSet;
    private GeoNoteDrawerViewModel geoNoteDrawerViewModel;
    protected GeoNoteExtension extension;

    public void addViewModel(GeoNoteDrawerViewModel geoNoteDrawerViewModel) {
        this.geoNoteDrawerViewModel = geoNoteDrawerViewModel;
    }


    // private GeoNoteDrawerFragment geoNoteDrawerFragment;
    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        private final GeoNoteDrawerViewModel geoNoteDrawerViewModel;
        private final GeoNoteExtension extension;


        public ViewHolder(View v, GeoNoteDrawerViewModel geoNoteDrawerViewModel, int[] images, String[] data, Extension extension){
            super(v);
            this.extension = (GeoNoteExtension) extension;
            this.geoNoteDrawerViewModel =geoNoteDrawerViewModel;
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
            GeoNoteType geoNoteType =null;
            switch (name) {//pick type
                case "Crop":
                    geoNoteType = GeoNoteType.CROP;
                    break;
                case "Garbage":
                    geoNoteType = GeoNoteType.TRASH;
                    break;
                case "Livestock":
                    geoNoteType = GeoNoteType.ANIMAL;
                    break;
                case "Pest":
                    geoNoteType = GeoNoteType.PEST;
                    break;
                case "Product":
                    geoNoteType = GeoNoteType.PRODUCT;
                    break;
                case "Spill":
                    geoNoteType = GeoNoteType.SPILL;
                    break;
                case "Weed":
                    geoNoteType = GeoNoteType.WEED;
                    break;
                case "Hazard":
                    geoNoteType = GeoNoteType.HAZARD;
                    break;
                case "Add a Picture":
                    Log.i(TAG, "Picture chosen");
                    return;
                case "Add a Recording":
                    Log.i(TAG, "Recording Chosen");
                    return;
                default:
                    Log.w(TAG, "Invalid Icon Type");
                    geoNoteType = GeoNoteType.HAZARD;
                    break;
            }

            Location location = extension.getCurrentLocation();
            Log.w(TAG, "Location: "+location.getLatitude()+", "+location.getLongitude());
            GeoNote geoNote   = new GeoNote();
            geoNote.setName(name);
            geoNote.setIcon(drawable);
            geoNote.setType(geoNoteType);
            geoNote.setPos(new double[]{location.getLatitude(), location.getLongitude()});
            //TODO Pictures/audio

            // geoNoteDrawerViewModel.insert(geoNote);
            geoNoteDrawerViewModel.addGeoNote(geoNote);
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
        return new ViewHolder(v, geoNoteDrawerViewModel,mImageSet, mDataSet,extension);
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
