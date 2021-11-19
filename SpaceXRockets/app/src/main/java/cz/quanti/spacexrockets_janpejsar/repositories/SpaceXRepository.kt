package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import cz.quanti.spacexrockets_janpejsar.entities.Rocket
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface SpaceXRepository {
    fun getRocketsFromAPI(): Single<List<Rocket>>

    fun saveRocketsToDatabase(
        context: Context,
        rockets: List<Rocket>
    )

    fun getSavedRocketsObservable(
        context: Context
    ): Observable<List<Rocket>>

    fun getRocketFromDatabase(
        context: Context,
        rocketId: String
    ): Observable<Rocket>
}