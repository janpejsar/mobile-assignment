package cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.MassApiEntity

data class MassEntity(
    val kg: Int,
    val lb: Int
) {
    constructor(mass: MassApiEntity): this(
        mass.kg,
        mass.lb
    )
}