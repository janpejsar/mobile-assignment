package cz.quanti.spacexrockets_janpejsar.rocketslist

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket
import java.util.*

data class RocketItem(
    val id: String,
    val name: String,
    val firstFlight: Date
) {
    constructor(rocket: Rocket): this(
        rocket.id,
        rocket.name,
        rocket.firstFlight
    )
}