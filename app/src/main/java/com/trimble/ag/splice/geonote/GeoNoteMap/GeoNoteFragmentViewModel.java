package com.trimble.ag.splice.geonote.GeoNoteMap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.trimble.ag.splice.geonote.Database.GeoNoteRepository;
import com.trimble.ag.splice.geonote.GeoNote;

import java.util.UUID;

public class GeoNoteFragmentViewModel extends ViewModel {
    private GeoNoteRepository mGeoNoteRepository;
    public GeoNoteFragmentViewModel (GeoNoteRepository geoNoteRepository){
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
    public void insertGeoNote(GeoNote geoNote){
        mGeoNoteRepository.addGeoNote(geoNote);
    }

}