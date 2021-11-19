package cz.quanti.spacexrockets_janpejsar.entities

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.StageApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.StageDbEntity

data class Stage(
    val reusable: Boolean,
    val engines: Int,
    val fuelAmount: Float,
    val burnTime: Int?
) {
    constructor(stage: StageApiEntity): this(
        stage.reusable,
        stage.engines,
        stage.fuelAmount,
        stage.burnTime
    )

    constructor(stage: StageDbEntity): this(
        stage.reusable,
        stage.engines,
        stage.fuelAmount,
        stage.burnTime
    )
}