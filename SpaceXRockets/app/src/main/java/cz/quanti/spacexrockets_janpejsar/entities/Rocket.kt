package cz.quanti.spacexrockets_janpejsar.entities

import com.google.gson.annotations.SerializedName

data class Rocket(
    val id: String,
    @SerializedName("name")
    val rocketName: String,
    val description: String,
    val height: Dimension,
    val diameter: Dimension,
    val mass: Mass,
    @SerializedName("first_stage")
    val firstStage: Stage,
    @SerializedName("second_stage")
    val secondStage: Stage
)