package cz.quanti.spacexrockets_janpejsar.spacexdatabase

import androidx.room.TypeConverter
import cz.quanti.spacexrockets_janpejsar.TimeUtils
import java.util.*

class DatabaseConverters {
    @TypeConverter
    fun fromApiStringDate(value: String): Date {
        return TimeUtils.fromApiStringDate(value)
    }

    @TypeConverter
    fun toApiStringDate(value: Date): String {
        return TimeUtils.toApiStringDate(value)
    }
}