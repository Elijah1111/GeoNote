package com.trimble.ag.splice.geonote.Database;

import android.util.Log;

import androidx.room.TypeConverter;

import com.trimble.ag.splice.geonote.GeoNoteType;

import java.util.UUID;

public class GeoNoteTypeConverter {
    @TypeConverter
    public UUID toUUID(String uuid){
        return(UUID.fromString(uuid));
    }

    @TypeConverter
    public String fromUUID(UUID uuid){
        return(uuid.toString());
    }

    @TypeConverter
    public String fromStringArray(String[] array){
        String stored = "";
        for(String element : array ){
            stored=stored+element+" ";
        }
        return(stored);
    }

    @TypeConverter
    public String[] toStringArray(String array){
        String[] data = array.split(" ");
        return(data);
    }

    @TypeConverter
    public String fromDoubleArray(double[] array){
        StringBuilder stored = new StringBuilder();
        for(double element : array ){
            stored.append(element).append(", ");
        }
        return(stored.toString());
    }

    @TypeConverter
    public double[] toDoubleArray(String array){
        String[] data = array.split(", ");
        double[] val = new double[data.length];
        for (int i =0; i<data.length; i++){
            double dub = Double.parseDouble(data[i]);
            val[i]=dub;
        }
        return(val);
    }
    @TypeConverter
    public GeoNoteType toGeoNoteType(String input){
        GeoNoteType geoNoteType;
        switch (input) {//pick type
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
            default:
                geoNoteType = GeoNoteType.HAZARD;
                break;
        }
        return geoNoteType;
    }
    @TypeConverter
    public String fromGeoNoteType (GeoNoteType Type){
        String geoNoteType;
        switch (Type) {//pick type
            case CROP:
                geoNoteType = "Crop";
                break;
            case TRASH:
                geoNoteType = "Garbage";
                break;
            case ANIMAL:
                geoNoteType = "Livestock";
                break;
            case PEST:
                geoNoteType = "Pest";
                break;
            case PRODUCT:
                geoNoteType = "Product";
                break;
            case SPILL:
                geoNoteType = "Spill";
                break;
            case WEED:
                geoNoteType = "Weed";
                break;
            case HAZARD:
                geoNoteType = "Hazard";
                break;
            default:
                geoNoteType = " ";
                break;
        }
        return geoNoteType;
    }
}

