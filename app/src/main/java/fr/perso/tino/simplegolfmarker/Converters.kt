package fr.perso.tino.simplegolfmarker

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimeStamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimeStamp(value: Date?): Long? {
        return value?.time?.toLong()
    }
}