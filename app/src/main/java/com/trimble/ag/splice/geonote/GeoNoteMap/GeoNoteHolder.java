package com.trimble.ag.splice.geonote.GeoNoteMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.trimble.ag.splice.geonote.GeoNote;
import com.trimble.ag.splice.geonote.R;
import com.trimble.ag.splice.geonote.databinding.GeonoteListItemBinding;
public class GeoNoteHolder extends RecyclerView.ViewHolder{

    GeonoteListItemBinding mBinding;
    private GeoNote geoNote;
    private final TextView geoNoteNameView;
    private final TextView geoNoteLocation;
    private final ImageView geoNoteImage;
    private static final String TAG = "GeoNoteListHolder";

    public GeoNoteHolder(View v) {
        super(v);
        geoNoteNameView = itemView.findViewById(R.id.geonote_title_text_view);
        geoNoteLocation = itemView.findViewById(R.id.geonote_loc_text_view);
        geoNoteImage = itemView.findViewById(R.id.geonote_image_view);
    }

    public void bind(GeoNote geoNote) {
        this.geoNote = geoNote;
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick (View v){
                Log.i(TAG, "View: "+geoNote.getName()+" clicked");
                GeoNoteFragment.clickedGeonote(geoNote);
                }
            });
        geoNoteNameView.setText(this.geoNote.getName());
        String location = this.geoNote.getPos()[0] +", "+ this.geoNote.getPos()[1];
        geoNoteLocation.setText(location);
        geoNoteImage.setImageResource(this.geoNote.getIcon());
    }

/*    static GeoNoteHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.geonote_list_item, parent, false);
        return new GeoNoteHolder(view);
    }*/

}
