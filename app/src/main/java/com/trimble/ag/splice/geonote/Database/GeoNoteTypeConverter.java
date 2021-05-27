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
}

