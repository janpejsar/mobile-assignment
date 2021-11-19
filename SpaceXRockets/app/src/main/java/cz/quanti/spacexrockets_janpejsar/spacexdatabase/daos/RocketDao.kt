package cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDbEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface RocketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rockets: List<RocketDbEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rocket: RocketDbEntity)

    @Query("SELECT * FROM rocket_table")
    fun getAllObservable(): Observable<List<RocketDbEntity>>

    @Query("SELECT * FROM rocket_table WHERE id = :rocketId")
    fun get(rocketId: String): RocketDbEntity

    @Query("SELECT * FROM rocket_table WHERE id = :rocketId")
    fun getObservable(rocketId: String): Observable<RocketDbEntity>
}