package com.trimble.ag.splice.geonote.GeoNoteDrawer;

import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.trimble.ag.splice.Extension;
import com.trimble.ag.splice.geonote.Database.GeoNoteRepository;
import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.GeoNoteExtension;
import com.trimble.ag.splice.geonote.GeoNoteType;
import com.trimble.ag.splice.geonote.R;
import com.trimble.ag.splice.location.Location;

import java.util.Objects;
import java.util.zip.Inflater;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.startActivity;

public class GeonoteDrawerAdapter extends RecyclerView.Adapter<GeonoteDrawerAdapter.ViewHolder>{

    private static final String TAG = "DrawerAdapter";
    private String[] mDataSet;
    private int[] mImageSet;
    private GeoNoteRepository repository;
    protected GeoNoteExtension extension;
    Button btnCam, btnMic;

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
        private GeoNoteExtension extension;


        public ViewHolder(View v, GeoNoteRepository mRepository, int[] images, String[] data, Extension extension){
            super(v);
            this.extension = (GeoNoteExtension) extension;
            repository =mRepository;
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data[getAdapterPosition()] == "Open Camera"){
                        Intent intent = new intent();
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(intent);
                    }
                    else {
                        Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                        addGeoNote(images[getAdapterPosition()], data[getAdapterPosition()]);
                        // geoNoteDrawerFragment.addGeoNote(image[getAdapterPosition()], data[getAdapterPosition()]);
                    }
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
            GeoNoteType geoNoteType;
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
                case "Record":
                    //TODO: Do something for the record
                    break;
                case "Camera":
                    //TODO: Do something
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // start the image capture Intent
                    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                    break;
                default:
                    Log.w(TAG, "Invalid Icon Type");
                    geoNoteType = GeoNoteType.HAZARD;
                    break;
            }

            Location location = extension.getCurrentLocation();
            Log.w(TAG, "Location: "+location.getLatitude()+", "+location.getLongitude());
            GeoNote geoNote   = new GeoNote(name,drawable,geoNoteType,
                    location.getLatitude(),location.getLongitude());
            //TODO Pictures/audio

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

        return new ViewHolder(v,repository,mImageSet, mDataSet,extension);
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
