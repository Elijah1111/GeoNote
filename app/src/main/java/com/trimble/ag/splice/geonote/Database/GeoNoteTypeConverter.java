package com.trimble.ag.splice.geonote.Database;

import androidx.room.TypeConverter;

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
}

