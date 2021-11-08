package cz.quanti.spacexrockets_janpejsar.entities

import com.squareup.moshi.Json

data class Rocket(
    @field:Json(name="id")
    val id: String,
    @field:Json(name="name")
    val rocketName: String,
    @field:Json(name="description")
    val description: String,
    @field:Json(name="height")
    val height: Dimension,
    @field:Json(name="diameter")
    val diameter: Dimension,
    @field:Json(name="mass")
    val mass: Mass,
    @field:Json(name="first_stage")
    val firstStage: Stage,
    @field:Json(name="second_stage")
    val secondStage: Stage
)