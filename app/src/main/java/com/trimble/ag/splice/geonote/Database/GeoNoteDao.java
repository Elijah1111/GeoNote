package com.trimble.ag.splice.geonote.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.trimble.ag.splice.geonote.GeoNote;

import java.util.List;
import java.util.UUID;

@Dao
public interface GeoNoteDao {
    @Query("SELECT * FROM geoNote")
    LiveData<List<GeoNote>> getNotes();
    @Query("SELECT * FROM geonote WHERE uid=(:arg0)")
    LiveData<GeoNote> getNote(UUID arg0);
    @Update
    void updateGeoNote(GeoNote geoNote);
    @Insert
    void addGeoNote(GeoNote geoNote);
    @Query("DELETE FROM geonote")
    void deleteAll();
    @Delete
    void deleteGeoNote(GeoNote geoNote);
}

