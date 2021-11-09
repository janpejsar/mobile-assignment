package cz.quanti.spacexrockets_janpejsar.spacexapi.entities

import com.squareup.moshi.Json
import java.util.*

data class RocketApiEntity(
    @field:Json(name="id")
    val id: String,
    @field:Json(name="name")
    val name: String,
    @field:Json(name="description")
    val description: String,
    @field:Json(name="height")
    val height: DimensionApiEntity,
    @field:Json(name="diameter")
    val diameter: DimensionApiEntity,
    @field:Json(name="mass")
    val mass: MassApiEntity,
    @field:Json(name="first_stage")
    val firstStage: StageApiEntity,
    @field:Json(name="second_stage")
    val secondStage: StageApiEntity,
    @field:Json(name="first_flight")
    val firstFlight: Date
)