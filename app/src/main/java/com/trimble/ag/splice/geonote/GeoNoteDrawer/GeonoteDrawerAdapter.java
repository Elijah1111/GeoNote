package com.trimble.ag.splice.geonote.GeoNoteDrawer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.trimble.ag.splice.Extension;
import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.GeoNoteExtension;
import com.trimble.ag.splice.geonote.GeoNoteMap.GeoNoteFragmentViewModel;
import com.trimble.ag.splice.geonote.GeoNoteType;
import com.trimble.ag.splice.geonote.R;
import com.trimble.ag.splice.location.Location;

import java.io.File;
import java.util.List;

public class GeonoteDrawerAdapter extends RecyclerView.Adapter<GeonoteDrawerAdapter.ViewHolder>{//Creates the view of all the icons in the GeoNote Drawer

    private static final String TAG = "GeoNoteDrawerAdapter";
    private final String[] mDataSet;
    private final int[] mImageSet;
    private GeoNoteDrawerViewModel geoNoteDrawerViewModel;
    protected GeoNoteExtension extension;
    private FragmentActivity activity;

    public void addViewModel(GeoNoteDrawerViewModel geoNoteDrawerViewModel, FragmentActivity fragmentActivity) {//Adds the GeoNoteDrawerViewModel to this class to maintain the instance
        this.geoNoteDrawerViewModel = geoNoteDrawerViewModel;
        activity=fragmentActivity;
    }


    // private GeoNoteDrawerFragment geoNoteDrawerFragment;
    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {//Inner class that represents each of the individual icons and their functionality.
        private final TextView textView;
        private final ImageView imageView;
        private final GeoNoteDrawerViewModel geoNoteDrawerViewModel;
        private final GeoNoteExtension extension;
        private Uri photoUri;
        private Uri audioUri;
        private static GeoNote oldGeoNote;
        private static boolean record = true;

        public ViewHolder(View v, GeoNoteDrawerViewModel geoNoteDrawerViewModel, int[] images, String[] data, Extension extension, FragmentActivity activity){//Constructor that sets the icon-specific data
            super(v);
            this.extension = (GeoNoteExtension) extension;
            this.geoNoteDrawerViewModel =geoNoteDrawerViewModel;
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    addGeoNote(images[getAdapterPosition()], data[getAdapterPosition()], activity);
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

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void addGeoNote(int drawable, String name, FragmentActivity activity) {
            GeoNoteType geoNoteType = null;
            GeoNote geoNote = new GeoNote();

            try {//Surrounded by a try catch in case the Picture or camera doesn't exist on the device
                Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent captureAudio = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            switch (name) {//picks the type and assign or execute necessary functions
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
                    captureImage.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri(geoNote));//Captures image and updates the last GeoNote created
                    activity.startActivityForResult(captureImage, 2);
                    oldGeoNote.setPictures(photoUri);
                    geoNoteDrawerViewModel.updateGeoNote(oldGeoNote);
                    return;
                case "Add a Recording":
                    Log.i(TAG, "Recording Chosen");
                    captureAudio.putExtra(MediaStore.EXTRA_OUTPUT, getAudioUri(geoNote));//Captures audio and updates the last GeoNote created
                    activity.startActivityForResult(captureAudio, 3);
                    oldGeoNote.setAudio(audioUri);
                    geoNoteDrawerViewModel.updateGeoNote(oldGeoNote);
                    return;

                default:
                    Log.w(TAG, "Invalid Icon Type");
                    geoNoteType = GeoNoteType.HAZARD;
                    break;
            }

            Location location = extension.getCurrentLocation();
            Log.w(TAG, "Location: " + location.getLatitude() + ", " + location.getLongitude());
            geoNote.setName(name);//Sets GeoNote parameters
            geoNote.setIcon(drawable);
            geoNote.setType(geoNoteType);
            geoNote.setPos(new double[]{location.getLatitude(), location.getLongitude()});
            oldGeoNote = geoNote;
            geoNoteDrawerViewModel.addGeoNote(geoNote);//Adds the GeoNote to the database
        }
            catch (Exception e){//Prints the exception if the camera/recording hardware fails
                Log.i(TAG, e.toString());
            }
        }

        private Uri getImageUri(GeoNote geoNote) {//Gets the file name for the picture from the UUID
            File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera", "IMG_"+geoNote.getUid().toString()+".jpg");
            photoUri = Uri.fromFile(file);

            return photoUri;
        }
        private Uri getAudioUri(GeoNote geoNote) {//Gets the file name for the audio from the UUID
            File file = new File(Environment.getExternalStorageDirectory() + "/DCIM", "Audio_"+geoNote.getUid().toString()+".mp3");
            audioUri = Uri.fromFile(file);

            return photoUri;
        }
    }

    public GeonoteDrawerAdapter(String[] dataSet, int[] imageSet){//Passes in the list information for the drawer
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
        return new ViewHolder(v, geoNoteDrawerViewModel,mImageSet, mDataSet,extension, activity);
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
