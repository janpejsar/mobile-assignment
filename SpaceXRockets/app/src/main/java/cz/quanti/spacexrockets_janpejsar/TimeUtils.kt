@file:JvmName("TimeUtils")
package cz.quanti.spacexrockets_janpejsar

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {
    fun toStringDate(date: Date): String {
        val format = SimpleDateFormat("d.M.yyyy", Locale.getDefault())
        return format.format(date)
    }

    fun fromApiStringDate(string: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(string) ?: Date()
    }

    fun toApiStringDate(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(date)
    }
}
