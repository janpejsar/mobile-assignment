package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXEndpoints
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.SpaceXRoomDatabase
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import io.reactivex.rxjava3.core.Observable

class ProductionSpaceXRepository(
    private val database: SpaceXRoomDatabase,
    private val service: SpaceXEndpoints
): SpaceXRepository {
    override fun getRocketsFromAPI(): Observable<List<RocketApiEntity>> {
        return service.getRockets()
    }

    override fun saveRocketsToDatabase(
        context: Context,
        rockets: List<RocketEntity>
    ) {
        database.rocketDao().insert(rockets)
    }

    override fun getSavedRocketsObservable(context: Context): Observable<List<RocketEntity>> {
        return database.rocketDao().getAllObservable()
    }

    override fun getRocketFromDatabase(
        context: Context,
        rocketId: String
    ): Observable<RocketEntity> {
        return database.rocketDao().getObservable(rocketId)
    }
}