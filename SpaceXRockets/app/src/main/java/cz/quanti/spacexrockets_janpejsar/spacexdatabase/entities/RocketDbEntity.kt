package cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.quanti.spacexrockets_janpejsar.entities.Rocket
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import java.util.*

@Entity(tableName = "rocket_table")
data class RocketDbEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val description: String,
    @Embedded(prefix = "height_")
    val height: DimensionDbEntity,
    @Embedded(prefix = "diameter_")
    val diameter: DimensionDbEntity,
    @Embedded(prefix = "mass_")
    val mass: MassDbEntity,
    @Embedded(prefix = "first_stage_")
    val firstStage: StageDbEntity,
    @Embedded(prefix = "second_stage_")
    val secondStage: StageDbEntity,
    @ColumnInfo(name = "first_flight")
    val firstFlight: Date,
    @ColumnInfo(name = "images")
    val images: List<String>
) {
    constructor(rocket: Rocket) : this(
        rocket.id,
        rocket.name,
        rocket.description,
        DimensionDbEntity(rocket.height),
        DimensionDbEntity(rocket.diameter),
        MassDbEntity(rocket.mass),
        StageDbEntity(rocket.firstStage),
        StageDbEntity(rocket.secondStage),
        rocket.firstFlight,
        rocket.images
    )
}