package com.trimble.ag.splice.geonote.GeoNoteDrawer

import androidx.lifecycle.ViewModel
import com.trimble.ag.splice.geonote.Database.GeoNoteRepository
import com.trimble.ag.splice.geonote.GeoNote
import java.io.File

class GeoNoteDrawerViewModel (private val geoNoteRepository: GeoNoteRepository)
    : ViewModel() {//Allows for the drawer to access the GeoNote repository instance
    val geoNoteLiveData = geoNoteRepository.getGeoNotes()
    fun addGeoNote(geoNote: GeoNote) {
        geoNoteRepository.addGeoNote(geoNote)
    }
    fun getPhotoFile(geoNote: GeoNote): File {
        return(geoNoteRepository.getPhotoFile(geoNote))
    }
    fun updateGeoNote(geoNote: GeoNote){
        geoNoteRepository.updateGeoNote(geoNote)
    }
}