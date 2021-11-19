package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import cz.quanti.spacexrockets_janpejsar.entities.Rocket
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXEndpoints
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos.RocketDao
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDbEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductionSpaceXRepository(
    private val rocketDao: RocketDao,
    private val service: SpaceXEndpoints
): SpaceXRepository {
    override fun getRocketsFromAPI(): Single<List<Rocket>> {
        return service.getRockets()
            .map {
                it.map { rocket -> Rocket(rocket) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    override fun saveRocketsToDatabase(
        context: Context,
        rockets: List<Rocket>
    ): Single<Unit> {
        return Single.fromCallable { rocketDao.insert(rockets.map { RocketDbEntity(it) }) }
    }

    override fun getSavedRocketsObservable(context: Context): Observable<List<Rocket>> {
        return rocketDao.getAllObservable()
            .map { it.map { rocket -> Rocket(rocket) } }
    }

    override fun getRocketFromDatabase(
        context: Context,
        rocketId: String
    ): Observable<Rocket> {
        return rocketDao.getObservable(rocketId)
            .map { Rocket(it) }
    }
}