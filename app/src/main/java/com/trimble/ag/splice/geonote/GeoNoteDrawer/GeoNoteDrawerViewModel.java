package com.trimble.ag.splice.geonote.GeoNoteDrawer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.trimble.ag.splice.geonote.Database.GeoNoteRepository;
import com.trimble.ag.splice.geonote.GeoNote;

import java.util.List;
import java.util.UUID;

public class GeoNoteDrawerViewModel extends ViewModel {
    private GeoNoteRepository mGeoNoteRepository;
    public GeoNoteDrawerViewModel (GeoNoteRepository geoNoteRepository){
        mGeoNoteRepository =geoNoteRepository;
    }
    private MutableLiveData<UUID> geoNoteIDLiveData = new MutableLiveData<UUID>();
    LiveData<GeoNote> geoNoteLiveData = Transformations.switchMap(geoNoteIDLiveData, geoNoteID-> mGeoNoteRepository.getGeoNote(geoNoteID));

    public void loadGeoNote(UUID geoNoteId){
        geoNoteIDLiveData.setValue(geoNoteId);
    }
    public void saveGeoNote(GeoNote geoNote){
        mGeoNoteRepository.updateGeoNote(geoNote);
    }

}
    /*private GeoNoteRepository mRepository;
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
}*/
