package cz.quanti.spacexrockets_janpejsar.spacexapi

import com.squareup.moshi.*
import cz.quanti.spacexrockets_janpejsar.TimeUtils
import java.util.*

class ApiDateAdapter: JsonAdapter<Date>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Date? {
        return TimeUtils.fromApiStringDate(reader.nextString())
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        if (value != null) {
            writer.value(TimeUtils.toApiStringDate(value))
        }
    }
}