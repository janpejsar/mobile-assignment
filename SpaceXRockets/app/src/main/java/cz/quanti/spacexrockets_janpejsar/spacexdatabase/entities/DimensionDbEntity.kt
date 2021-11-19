package cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities

import cz.quanti.spacexrockets_janpejsar.entities.Dimension

data class DimensionDbEntity(
    val meters: Float,
    val feet: Float
) {
    constructor(dimension: Dimension): this(
        dimension.meters,
        dimension.feet
    )
}