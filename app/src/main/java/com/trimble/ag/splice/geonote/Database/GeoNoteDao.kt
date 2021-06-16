package com.trimble.ag.splice.geonote.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.trimble.ag.splice.geonote.GeoNote
import java.util.*

@Dao
interface GeoNoteDao {
    @Query("SELECT * FROM geoNote")
    fun getNotes(): LiveData<List<GeoNote>>

    @Query("SELECT * FROM geonote WHERE uid=(:id)")
    fun getNote(id: UUID): LiveData<GeoNote?>

    @Update
    fun updateGeoNote(geoNote: GeoNote)

    @Insert
    fun addGeoNote(geoNote: GeoNote)

    @Query("DELETE FROM geonote")
    fun deleteAll()

    @Delete
    fun deleteGeoNote(geoNote: GeoNote)
}