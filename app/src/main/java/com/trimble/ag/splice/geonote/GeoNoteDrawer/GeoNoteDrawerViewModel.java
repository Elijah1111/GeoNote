package com.trimble.ag.splice.geonote.GeoNoteDrawer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.trimble.ag.splice.geonote.Database.GeoNoteRepository;
import com.trimble.ag.splice.geonote.GeoNote;

import java.util.List;

public class GeoNoteDrawerViewModel extends AndroidViewModel {
    private GeoNoteRepository mRepository;
    private final LiveData<List<GeoNote>> mAllNotes;
    private GeoNoteDrawerFragment geoNoteDrawerFragment;
    public GeoNoteDrawerViewModel(@NonNull Application application) {
        super(application);
        mRepository = new GeoNoteRepository(application);
        mAllNotes = mRepository.getGeoNotes();
    }

    void insert(GeoNote geoNote) {
        mRepository.addGeoNote(geoNote);
    }

    public LiveData<List<GeoNote>> getAllNotes() {
        return mRepository.getGeoNotes();
    }
}
