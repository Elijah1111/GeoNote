package com.trimble.ag.splice.geonote.Database;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.trimble.ag.splice.geonote.GeoNote;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.jvm.Synchronized;

public class GeoNoteRepository{
    private GeoNoteRepository(GeoNoteDao geoNoteDao){

    }
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private static GeoNoteRepository INSTANCE = null;
    @Synchronized
    public static GeoNoteRepository getInstance(Context context){
            GeoNoteRepository instance =INSTANCE;
            if(instance==null){
                GeoNoteDatabase database = GeoNoteDatabase.getInstance(context);
                instance = new GeoNoteRepository(database.GeoNoteDao());
                INSTANCE =instance;
            }
            return instance;
    }
    /*private GeoNoteDao geoNoteDao;
    private ExecutorService executor = Executors.newSingleThreadExecutor();



    public LiveData<List<GeoNote>> getGeoNotes(){
        return (geoNoteDao.getNotes());
    }
    public LiveData<GeoNote> getGeoNote(UUID id) {
        return (geoNoteDao.getNote(id));

    }
    public void updateGeoNote(GeoNote geoNote){
        executor.execute(()->{
            geoNoteDao.updateGeoNote(geoNote);
        });
    }
    public void deleteGeoNote(GeoNote geoNote){
        executor.execute(()->{
            geoNoteDao.deleteGeoNote(geoNote);
        });
    }
    public void addGeoNote(GeoNote geoNote){
        executor.execute(()->{
            geoNoteDao.addGeoNote(geoNote);
        });
    }*/



}

