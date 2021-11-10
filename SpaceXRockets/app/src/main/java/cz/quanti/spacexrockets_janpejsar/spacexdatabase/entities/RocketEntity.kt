package cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import java.util.*

@Entity(tableName = "rocket_table")
data class RocketEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val description: String,
    @Embedded(prefix = "height_")
    val height: DimensionEntity,
    @Embedded(prefix = "diameter_")
    val diameter: DimensionEntity,
    @Embedded(prefix = "mass_")
    val mass: MassEntity,
    @Embedded(prefix = "first_stage_")
    val firstStage: StageEntity,
    @Embedded(prefix = "second_stage_")
    val secondStage: StageEntity,
    @ColumnInfo(name = "first_flight")
    val firstFlight: Date,
    @ColumnInfo(name = "images")
    val images: List<String>
) {
    constructor(rocket: RocketApiEntity) : this(
        rocket.id,
        rocket.name,
        rocket.description,
        DimensionEntity(rocket.height),
        DimensionEntity(rocket.diameter),
        MassEntity(rocket.mass),
        StageEntity(rocket.firstStage),
        StageEntity(rocket.secondStage),
        rocket.firstFlight,
        rocket.images
    )
}