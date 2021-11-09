package cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Mass

data class MassDatabaseEntity(
    val kg: Int,
    val lb: Int
) {
    constructor(mass: Mass): this(
        mass.kg,
        mass.lb
    )
}