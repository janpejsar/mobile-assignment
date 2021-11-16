package cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface RocketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rockets: List<RocketEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rocket: RocketEntity)

    @Query("SELECT * FROM rocket_table")
    fun getAllObservable(): Observable<List<RocketEntity>>

    @Query("SELECT * FROM rocket_table WHERE id = :rocketId")
    fun get(rocketId: String): RocketEntity

    @Query("SELECT * FROM rocket_table WHERE id = :rocketId")
    fun getObservable(rocketId: String): Observable<RocketEntity>
}