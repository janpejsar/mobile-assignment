package cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities

import androidx.room.ColumnInfo
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Stage

data class StageDatabaseEntity(
    val reusable: Boolean,
    val engines: Int,
    @ColumnInfo(name = "fuel_amount")
    val fuelAmount: Float,
    @ColumnInfo(name = "burn_time")
    val burnTime: Int?
) {
    constructor(stage: Stage): this(
        stage.reusable,
        stage.engines,
        stage.fuelAmount,
        stage.burnTime
    )
}