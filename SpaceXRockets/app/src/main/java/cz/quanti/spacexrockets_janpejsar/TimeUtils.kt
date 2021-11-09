package cz.quanti.spacexrockets_janpejsar

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    companion object {
        @JvmStatic
        fun toStringDate(date: Date): String {
            val format = SimpleDateFormat("d.M.yyyy", Locale.getDefault())
            return format.format(date)
        }

        @JvmStatic
        fun fromApiStringDate(string: String): Date? {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return format.parse(string)
        }

        @JvmStatic
        fun toApiStringDate(date: Date): String {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return format.format(date)
        }
    }
}