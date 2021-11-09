package cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDatabaseEntity

@Dao
interface RocketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rockets: List<RocketDatabaseEntity>)

    @Query("SELECT * FROM rocket_table")
    fun getAll(): LiveData<List<RocketDatabaseEntity>>

    @Query("SELECT * FROM rocket_table WHERE id = :rocketId")
    fun get(rocketId: String): LiveData<RocketDatabaseEntity>
}