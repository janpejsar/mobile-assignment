package cz.quanti.spacexrockets_janpejsar.spacexdatabase

import androidx.room.TypeConverter
import cz.quanti.spacexrockets_janpejsar.fromApiStringDate as TimeUtils_fromApiStringDate
import cz.quanti.spacexrockets_janpejsar.toApiStringDate as TimeUtils_toApiStringDate
import java.util.*

class DatabaseConverters {
    @TypeConverter
    fun fromApiStringDate(value: String): Date {
        return TimeUtils_fromApiStringDate(value)
    }

    @TypeConverter
    fun toApiStringDate(value: Date): String {
        return TimeUtils_toApiStringDate(value)
    }
}