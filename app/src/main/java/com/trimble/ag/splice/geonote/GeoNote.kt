package com.trimble.ag.splice.geonote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class GeoNote(
    @PrimaryKey
    val uid:UUID = UUID.randomUUID(), //TODO I think this should be a final
    var name:String = "GeoNote",

    var type: GeoNoteType? = null,

    var pictures:String = "pics", //path to stored pictures

    var audio:String = "sound", //path to audio clip

    var icon:Int = 0,

    var pos: DoubleArray = DoubleArray(2) //The Latitude and Longitude
    )
