package cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Dimension

data class DimensionDatabaseEntity(
    val meters: Float,
    val feet: Float
) {
    constructor(dimension: Dimension): this(
        dimension.meters,
        dimension.feet
    )
}