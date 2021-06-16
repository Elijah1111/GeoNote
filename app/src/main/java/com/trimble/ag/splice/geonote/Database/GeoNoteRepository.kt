package com.trimble.ag.splice.geonote.Database

import android.content.Context
import androidx.lifecycle.LiveData
import com.trimble.ag.splice.geonote.GeoNote
import java.io.File
import java.util.*
import java.util.concurrent.Executors

class GeoNoteRepository private constructor (private val geoNoteDao: GeoNoteDao, context: Context)
{
    private val executor = Executors.newSingleThreadExecutor()
    private val filesDir = context.applicationContext.filesDir
    fun getGeoNotes(): LiveData<List<GeoNote>> =geoNoteDao.getNotes()
    fun getGeoNote(id: UUID): LiveData<GeoNote?> = geoNoteDao.getNote(id)
    fun updateGeoNote(geoNote: GeoNote){
        executor.execute{
            geoNoteDao.updateGeoNote(geoNote)
        }
    }
    fun addGeoNote(geoNote: GeoNote){
        executor.execute{
            geoNoteDao.addGeoNote(geoNote)
        }
    }
    fun deleteGeoNote(geoNote: GeoNote){
        executor.execute{
            geoNoteDao.deleteGeoNote(geoNote)
        }
    }
    companion object {
        private var INSTANCE: GeoNoteRepository? = null
        fun getInstance(context: Context): GeoNoteRepository {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    val database = GeoNoteDatabase.getInstance(context)
                    instance = GeoNoteRepository(database.geoNoteDao, context)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
    fun getPhotoFile(geoNote: GeoNote):File = File(filesDir, geoNote.photoFileName)
}
