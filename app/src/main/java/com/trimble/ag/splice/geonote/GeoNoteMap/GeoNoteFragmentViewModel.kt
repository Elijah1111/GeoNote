package com.trimble.ag.splice.geonote.GeoNoteMap

import androidx.lifecycle.ViewModel
import com.trimble.ag.splice.geonote.Database.GeoNoteRepository
import com.trimble.ag.splice.geonote.GeoNote
import java.io.File

class GeoNoteFragmentViewModel (private val geoNoteRepository: GeoNoteRepository)
    : ViewModel() {
    val geoNoteLiveData = geoNoteRepository.getGeoNotes()
    fun addGeoNote(geoNote: GeoNote) {
        geoNoteRepository.addGeoNote(geoNote)
    }
    fun deleteGeonote (geoNote: GeoNote){
        geoNoteRepository.deleteGeoNote(geoNote)
    }
    fun getPhotoFile(geoNote: GeoNote): File {
        return(geoNoteRepository.getPhotoFile(geoNote))
    }
}