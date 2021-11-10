package cz.quanti.spacexrockets_janpejsar.rocketslist

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import java.util.*

data class RocketItem(
    val id: String,
    val name: String,
    val firstFlight: Date
) {
    constructor(rocket: RocketApiEntity): this(
        rocket.id,
        rocket.name,
        rocket.firstFlight
    )

    constructor(rocket: RocketEntity): this(
        rocket.id,
        rocket.name,
        rocket.firstFlight
    )
}