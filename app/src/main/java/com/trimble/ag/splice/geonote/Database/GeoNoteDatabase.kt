package com.trimble.ag.splice.geonote.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.trimble.ag.splice.geonote.GeoNote

@Database(entities = [ GeoNote::class ], version = 1)
@TypeConverters(GeoNoteTypeConverter::class)
abstract class GeoNoteDatabase: RoomDatabase() {//Creates class framework/instance for the database
    abstract val geoNoteDao:GeoNoteDao
    companion object {
        private const val DATABASE_NAME = "geonotesdata"//Used to set the name
        private var INSTANCE: GeoNoteDatabase? = null

        fun getInstance(context: Context): GeoNoteDatabase {//Returns the instance for use in the GeoNote Repository
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context,
                        GeoNoteDatabase::class.java,
                        DATABASE_NAME)
                        .build()
                }
                return instance
            }
        }
    }
}