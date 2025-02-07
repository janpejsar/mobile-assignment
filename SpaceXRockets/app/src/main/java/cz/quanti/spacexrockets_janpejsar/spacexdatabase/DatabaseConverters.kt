package cz.quanti.spacexrockets_janpejsar.spacexdatabase

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import cz.quanti.spacexrockets_janpejsar.TimeUtils
import java.util.*
import kotlin.collections.ArrayList

class DatabaseConverters {
    @TypeConverter
    fun fromApiStringDate(value: String): Date {
        return TimeUtils.fromApiStringDate(value)
    }

    @TypeConverter
    fun toApiStringDate(value: Date): String {
        return TimeUtils.toApiStringDate(value)
    }

    @TypeConverter
    fun stringToStringList(value: String): List<String> {
        if (value.isEmpty()) {
            return ArrayList()
        }

        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val jsonAdapter = moshi.adapter<List<String>>(type)

        return jsonAdapter.fromJson(value) ?: ArrayList()
    }

    @TypeConverter
    fun stringListToString(value: List<String>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val jsonAdapter = moshi.adapter<List<String>>(type)

        return jsonAdapter.toJson(value)
    }
}