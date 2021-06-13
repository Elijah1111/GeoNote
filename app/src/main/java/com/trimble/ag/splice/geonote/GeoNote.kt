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
    ) {
    constructor(Note: String, crop: Int, animal: GeoNoteType, arg4: Double, arg5: Double) : this() {
        name = Note
        icon = crop
        type = animal
        pos[0]=arg4
        pos[1]=arg5
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GeoNote

        if (!pos.contentEquals(other.pos)) return false

        return true
    }

    override fun hashCode(): Int {
        return pos.contentHashCode()
    }
}