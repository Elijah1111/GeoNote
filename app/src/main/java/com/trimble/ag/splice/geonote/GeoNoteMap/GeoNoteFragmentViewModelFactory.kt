package com.trimble.ag.splice.geonote.GeoNoteMap

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trimble.ag.splice.geonote.Database.GeoNoteRepository

class GeoNoteFragmentViewModelFactory (private val context: Context)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GeoNoteRepository::class.java)
            .newInstance(GeoNoteRepository.getInstance(context))
    }
}