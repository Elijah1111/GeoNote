package com.trimble.ag.splice.geonote;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class GeoNote {
    @PrimaryKey
    @NonNull
    public UUID uid =   java.util.UUID.randomUUID();
    @ColumnInfo(name = "name")
    public String name = "GeoNote";
    @ColumnInfo(name = "type")
    public GeoNoteType type = null;
    @ColumnInfo(name = "pictures")
    public String[] pictures = {"", ""};//path to stored pictures
    @ColumnInfo(name = "audio")
    public String audio = "";//path to audio clip
    @ColumnInfo(name = "icon")
    public int icon = 0;
    @ColumnInfo(name = "position")
    public double[] pos = new double[]{0, 0};//The Latitude and Longitude
}
   /* public GeoNote(String n, int icon, GeoNoteType t,double lat, double lon){
        this.name = n;
        this.icon = icon;//TODO
        this.type = t;
        pos[0] = lat;
        pos[1] = lon;
    }

    public UUID getUid(){
        return uid;
    }
    public void addAudio(String a){
        //TODO make sure this exists
        audio = a;
    }
    public String getAudio(){
        return audio;
    }

    public void addImages(String p1, String p2){
        //TODO make sure these exist
        pictures[0] = p1;
        pictures[1] = p2;
    }
    public String[] getPictures(){//return path to pictures
        return pictures;
    }
    public int getIcon() {//return icon to draw
        return icon;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public GeoNoteType getType(){
        return type;
    }
    public void setType(GeoNoteType t){
        this.type = t;
    }
    public double[] getPos(){
        return pos;
    }
}*/
