package cz.quanti.spacexrockets_janpejsar.entities

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDbEntity
import java.util.*

data class Rocket(
    val id: String,
    val name: String,
    val description: String,
    val height: Dimension,
    val diameter: Dimension,
    val mass: Mass,
    val firstStage: Stage,
    val secondStage: Stage,
    val firstFlight: Date,
    val images: List<String>
) {
    constructor(rocket: RocketApiEntity): this(
        rocket.id,
        rocket.name,
        rocket.description,
        Dimension(rocket.height),
        Dimension(rocket.diameter),
        Mass(rocket.mass),
        Stage(rocket.firstStage),
        Stage(rocket.secondStage),
        rocket.firstFlight,
        rocket.images
    )

    constructor(rocket: RocketDbEntity): this(
        rocket.id,
        rocket.name,
        rocket.description,
        Dimension(rocket.height),
        Dimension(rocket.diameter),
        Mass(rocket.mass),
        Stage(rocket.firstStage),
        Stage(rocket.secondStage),
        rocket.firstFlight,
        rocket.images
    )
}