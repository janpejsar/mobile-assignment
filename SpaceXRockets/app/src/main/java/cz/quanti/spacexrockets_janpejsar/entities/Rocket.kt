package cz.quanti.spacexrockets_janpejsar.entities

import com.squareup.moshi.Json

data class Rocket(
    @field:Json(name="id")
    val id: String,
    @field:Json(name="name")
    val name: String,
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
    val secondStage: Stage,
    @field:Json(name="first_flight")
    val firstFlight: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rocket

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (height != other.height) return false
        if (diameter != other.diameter) return false
        if (mass != other.mass) return false
        if (firstStage != other.firstStage) return false
        if (secondStage != other.secondStage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + diameter.hashCode()
        result = 31 * result + mass.hashCode()
        result = 31 * result + firstStage.hashCode()
        result = 31 * result + secondStage.hashCode()
        return result
    }
}