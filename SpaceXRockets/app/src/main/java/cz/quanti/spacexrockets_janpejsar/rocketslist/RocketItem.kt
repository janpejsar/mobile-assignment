package cz.quanti.spacexrockets_janpejsar.rocketslist

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDatabaseEntity
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

    constructor(rocket: RocketDatabaseEntity): this(
        rocket.id,
        rocket.name,
        rocket.firstFlight
    )
}