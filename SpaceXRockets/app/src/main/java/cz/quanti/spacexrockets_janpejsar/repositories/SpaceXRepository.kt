package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import io.reactivex.rxjava3.core.Observable

interface SpaceXRepository {
    fun getRocketsFromAPI(): Observable<List<RocketApiEntity>>

    fun saveRocketsToDatabase(
        context: Context,
        rockets: List<RocketEntity>
    )

    fun getSavedRocketsLiveData(
        context: Context
    ): Observable<List<RocketEntity>>

    fun getRocketFromDatabase(
        context: Context,
        rocketId: String
    ): Observable<RocketEntity>
}