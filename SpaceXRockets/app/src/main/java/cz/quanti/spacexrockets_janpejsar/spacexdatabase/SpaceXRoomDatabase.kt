package cz.quanti.spacexrockets_janpejsar.spacexdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos.RocketDao
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity

const val DATABASE_NAME = "space_x_database"

@Database(version = 1, entities = [RocketEntity::class], exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class SpaceXRoomDatabase: RoomDatabase() {
    abstract fun rocketDao(): RocketDao
}