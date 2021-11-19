package cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities

import cz.quanti.spacexrockets_janpejsar.entities.Mass

data class MassDbEntity(
    val kg: Int,
    val lb: Int
) {
    constructor(mass: Mass): this(
        mass.kg,
        mass.lb
    )
}