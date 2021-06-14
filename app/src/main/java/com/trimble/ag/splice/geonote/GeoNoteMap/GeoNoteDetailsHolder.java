package com.trimble.ag.splice.geonote.GeoNoteMap;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.R;
import com.trimble.ag.splice.geonote.databinding.GeonoteListItemBinding;

public class GeoNoteDetailsHolder extends RecyclerView.ViewHolder {

    GeonoteListItemBinding mBinding;
    private GeoNote geoNote;
    private GeoNoteFragmentViewModel geoNoteFragmentViewModel;
    private final TextView geoNoteNameView;
    private final TextView geoNoteLocation;
    private final ImageView geoNoteImage;
    private final ImageView geoNotePicture;
    private final Button back;
    private final Button delete;
    private static final String TAG = "GeoNoteListHolder";

    public GeoNoteDetailsHolder(View v, GeoNoteFragmentViewModel geoNoteFragmentViewModel) {
        super(v);
        this.geoNoteFragmentViewModel = geoNoteFragmentViewModel;
        geoNoteNameView = itemView.findViewById(R.id.note_name_text_view);
        geoNoteLocation = itemView.findViewById(R.id.note_loc_text_view);
        geoNoteImage = itemView.findViewById(R.id.geonote_detail_image);
        geoNotePicture = itemView.findViewById(R.id.note_image);
        back = itemView.findViewById(R.id.back_button);
        delete = itemView.findViewById(R.id.delete_button);
    }

    public void bind(GeoNote geoNote) {
        this.geoNote = geoNote;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Back button clicked");
                GeoNoteFragment.backToList();
            }
        });
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Delete button clicked");
                geoNoteFragmentViewModel.deleteGeonote(geoNote);
                GeoNoteFragment.backToList();
            }
        });
        if(geoNote.getPictures() != null){
            geoNotePicture.setVisibility(View.VISIBLE);
            geoNotePicture.setImageURI(geoNote.getPictures());
        }
        geoNoteNameView.setText(this.geoNote.getName());
        String location = this.geoNote.getPos()[0] + ", " + this.geoNote.getPos()[1];
        geoNoteLocation.setText(location);
        geoNoteImage.setImageResource(this.geoNote.getIcon());
    }
}