package com.trimble.ag.splice.geonote.GeoNoteDrawer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trimble.ag.splice.geonote.R;

public class GeonoteDrawerAdapter extends RecyclerView.Adapter<GeonoteDrawerAdapter.ViewHolder> {
    private static final String TAG = "DrawerAdapter";
    private String[] mDataSet;
    private int[] mImageSet;
    private GeoNoteDrawerFragment geoNoteDrawerFragment;
    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        public ViewHolder(View v, GeoNoteDrawerFragment geoNoteDrawerFragment) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");

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
        geoNoteDrawerFragment = new GeoNoteDrawerFragment();
        return new ViewHolder(v, geoNoteDrawerFragment);
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
