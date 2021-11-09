package cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket
import java.util.*

@Entity(tableName = "rocket_table")
data class RocketDatabaseEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val description: String,
    @Embedded(prefix = "height_")
    val height: DimensionDatabaseEntity,
    @Embedded(prefix = "diameter_")
    val diameter: DimensionDatabaseEntity,
    @Embedded(prefix = "mass_")
    val mass: MassDatabaseEntity,
    @Embedded(prefix = "first_stage_")
    val firstStage: StageDatabaseEntity,
    @Embedded(prefix = "second_stage_")
    val secondStage: StageDatabaseEntity,
    @ColumnInfo(name = "first_flight")
    val firstFlight: Date
) {
    constructor(rocket: Rocket) : this(
        rocket.id,
        rocket.name,
        rocket.description,
        DimensionDatabaseEntity(rocket.height),
        DimensionDatabaseEntity(rocket.diameter),
        MassDatabaseEntity(rocket.mass),
        StageDatabaseEntity(rocket.firstStage),
        StageDatabaseEntity(rocket.secondStage),
        rocket.firstFlight
    )
}