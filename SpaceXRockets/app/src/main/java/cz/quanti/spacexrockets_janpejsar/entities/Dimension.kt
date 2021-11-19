package cz.quanti.spacexrockets_janpejsar.entities

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.DimensionApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.DimensionDbEntity

data class Dimension(
    val meters: Float,
    val feet: Float
) {
    constructor(dimension: DimensionApiEntity): this(
        dimension.meters,
        dimension.feet
    )

    constructor(dimension: DimensionDbEntity): this(
        dimension.meters,
        dimension.feet
    )
}