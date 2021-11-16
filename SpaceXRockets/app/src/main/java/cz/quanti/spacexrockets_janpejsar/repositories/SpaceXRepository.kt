package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface SpaceXRepository {
    fun getRocketsFromAPI(): Single<List<RocketApiEntity>>

    fun saveRocketsToDatabase(
        context: Context,
        rockets: List<RocketEntity>
    )

    fun getSavedRocketsObservable(
        context: Context
    ): Observable<List<RocketEntity>>

    fun getRocketFromDatabase(
        context: Context,
        rocketId: String
    ): Observable<RocketEntity>
}