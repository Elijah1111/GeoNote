package com.trimble.ag.splice.geonote.GeoNoteDrawer

import androidx.lifecycle.ViewModel
import com.trimble.ag.splice.geonote.Database.GeoNoteRepository
import com.trimble.ag.splice.geonote.GeoNote

class GeoNoteDrawerViewModel (private val geoNoteRepository: GeoNoteRepository)
    : ViewModel() {
    val geoNoteLiveData = geoNoteRepository.getGeoNotes()
    fun addGeoNote(geoNote: GeoNote) {
        geoNoteRepository.addGeoNote(geoNote)
    }
}