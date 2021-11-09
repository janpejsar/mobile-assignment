package cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDatabaseEntity

@Dao
interface RocketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rockets: List<RocketDatabaseEntity>)
}