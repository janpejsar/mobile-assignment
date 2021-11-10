package cz.quanti.spacexrockets_janpejsar.spacexapi

import com.squareup.moshi.*
import cz.quanti.spacexrockets_janpejsar.fromApiStringDate
import cz.quanti.spacexrockets_janpejsar.toApiStringDate
import java.util.*

class ApiDateAdapter: JsonAdapter<Date>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Date {
        return fromApiStringDate(reader.nextString())
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        if (value != null) {
            writer.value(toApiStringDate(value))
        }
    }
}