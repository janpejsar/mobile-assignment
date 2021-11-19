package cz.quanti.spacexrockets_janpejsar.repositories

import cz.quanti.spacexrockets_janpejsar.entities.Rocket
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface SpaceXRepository {
    fun getRocketsFromAPI(): Single<List<Rocket>>

    fun saveRocketsToDatabase(
        rockets: List<Rocket>
    ): Completable

    fun getSavedRocketsObservable(
    ): Observable<List<Rocket>>

    fun getRocketFromDatabase(
        rocketId: String
    ): Observable<Rocket>
}