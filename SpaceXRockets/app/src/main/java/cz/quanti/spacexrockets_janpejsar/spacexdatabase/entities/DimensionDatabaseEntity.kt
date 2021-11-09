package cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.DimensionApiEntity

data class DimensionDatabaseEntity(
    val meters: Float,
    val feet: Float
) {
    constructor(dimension: DimensionApiEntity): this(
        dimension.meters,
        dimension.feet
    )
}