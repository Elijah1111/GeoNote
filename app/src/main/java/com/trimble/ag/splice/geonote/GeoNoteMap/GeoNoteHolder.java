package com.trimble.ag.splice.geonote.GeoNoteMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.trimble.ag.splice.geonote.R;

import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.databinding.GeonoteListItemBinding;
public class GeoNoteHolder extends RecyclerView.ViewHolder{
    GeonoteListItemBinding mBinding;
    private GeoNote geoNote;
    /*private final TextView geoNoteNameView;
    private final TextView geoNoteLocation;
    private final ImageView geoNoteImage;*/

    public GeoNoteHolder(GeonoteListItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
/*        geoNoteNameView = itemView.findViewById(R.id.geonote_title_text_view);
        geoNoteLocation = itemView.findViewById(R.id.geonote_loc_text_view);
        geoNoteImage = itemView.findViewById(R.id.geonote_image_view);*/
    }

    public void bind(GeoNote geoNote) {
        this.geoNote = geoNote;
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick (View v){
                //todo click stuff
                }
            });
        mBinding.geonoteTitleTextView.setText(this.geoNote.name);
        String location = this.geoNote.pos[0] +", "+ this.geoNote.pos[1];
        mBinding.geonoteLocTextView.setText(location);
        mBinding.geonoteImageView.setImageResource(this.geoNote.icon);
/*        geoNoteNameView.setText(geoNote.name);
        String location = geoNote.pos[0] +", "+ geoNote.pos[1];
        geoNoteLocation.setText(location);
        geoNoteImage.setImageResource(geoNote.icon);*/
    }

/*    static GeoNoteHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.geonote_list_item, parent, false);
        return new GeoNoteHolder(view);
    }*/

}
