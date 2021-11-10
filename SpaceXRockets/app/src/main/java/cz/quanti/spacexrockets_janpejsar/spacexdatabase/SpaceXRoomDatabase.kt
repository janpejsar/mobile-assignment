package cz.quanti.spacexrockets_janpejsar.spacexdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos.RocketDao
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity

@Database(version = 1, entities = [RocketEntity::class], exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class SpaceXRoomDatabase: RoomDatabase() {
    abstract fun rocketDao(): RocketDao

    companion object {
        private const val DATABASE_NAME = "space_x_database"
        @Volatile
        private var instance: SpaceXRoomDatabase? = null

        fun getDatabase(context: Context): SpaceXRoomDatabase {
            return if (instance == null) {
                synchronized(SpaceXRoomDatabase::class) {
                    instance = Room.databaseBuilder(context, SpaceXRoomDatabase::class.java, DATABASE_NAME)
                        .build()
                }

                instance as SpaceXRoomDatabase
            } else {
                instance as SpaceXRoomDatabase
            }
        }
    }
}