package cz.quanti.spacexrockets_janpejsar.entities

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.MassApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.MassDbEntity

data class Mass(
    val kg: Int,
    val lb: Int
) {
    constructor(mass: MassApiEntity): this(
        mass.kg,
        mass.lb
    )

    constructor(mass: MassDbEntity): this(
        mass.kg,
        mass.lb
    )
}