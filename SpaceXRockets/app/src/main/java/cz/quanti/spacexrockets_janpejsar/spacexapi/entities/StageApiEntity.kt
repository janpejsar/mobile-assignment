package cz.quanti.spacexrockets_janpejsar.spacexapi.entities

import com.squareup.moshi.Json

data class StageApiEntity(
    @field:Json(name="reusable")
    val reusable: Boolean,
    @field:Json(name="engines")
    val engines: Int,
    @field:Json(name="fuel_amount_tons")
    val fuelAmount: Float,
    @field:Json(name="burn_time_sec")
    val burnTime: Int?
)