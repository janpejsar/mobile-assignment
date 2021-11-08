package cz.quanti.spacexrockets_janpejsar.entities

import com.squareup.moshi.Json

data class Stage(
    @field:Json(name="reusable")
    val reusable: Boolean,
    @field:Json(name="engines")
    val engines: Int,
    @field:Json(name="fuel_amount_tons")
    val fuelAmount: Float,
    @field:Json(name="burn_time_sec")
    val burnTime: Int?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Stage

        if (reusable != other.reusable) return false
        if (engines != other.engines) return false
        if (fuelAmount != other.fuelAmount) return false
        if (burnTime != other.burnTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = reusable.hashCode()
        result = 31 * result + engines
        result = 31 * result + fuelAmount.hashCode()
        result = 31 * result + (burnTime ?: 0)
        return result
    }
}