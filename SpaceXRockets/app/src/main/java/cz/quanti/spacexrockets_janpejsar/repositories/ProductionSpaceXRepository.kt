package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXEndpoints
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos.RocketDao
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class ProductionSpaceXRepository(
    private val rocketDao: RocketDao,
    private val service: SpaceXEndpoints
): SpaceXRepository {
    override fun getRocketsFromAPI(): Single<List<RocketApiEntity>> {
        return service.getRockets()
    }

    override fun saveRocketsToDatabase(
        context: Context,
        rockets: List<RocketEntity>
    ) {
        rocketDao.insert(rockets)
    }

    override fun getSavedRocketsObservable(context: Context): Observable<List<RocketEntity>> {
        return rocketDao.getAllObservable()
    }

    override fun getRocketFromDatabase(
        context: Context,
        rocketId: String
    ): Observable<RocketEntity> {
        return rocketDao.getObservable(rocketId)
    }
}