package cz.quanti.spacexrockets_janpejsar.rocketslist

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDatabaseEntity
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

    constructor(rocket: RocketDatabaseEntity): this(
        rocket.id,
        rocket.name,
        rocket.firstFlight
    )
}