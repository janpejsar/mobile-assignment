package cz.quanti.spacexrockets_janpejsar.entities

import com.google.gson.annotations.SerializedName

data class Stage(
    val reusable: Boolean,
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmount: Float,
    @SerializedName("burn_time_sec")
    val burnTime: Int
)