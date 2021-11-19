package cz.quanti.spacexrockets_janpejsar.spacexdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos.RocketDao
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDbEntity

@Database(version = 2, entities = [RocketDbEntity::class], exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class SpaceXRoomDatabase: RoomDatabase() {
    abstract fun rocketDao(): RocketDao

    companion object {
        const val DATABASE_NAME = "space_x_database"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE rocket_table ADD COLUMN images TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}