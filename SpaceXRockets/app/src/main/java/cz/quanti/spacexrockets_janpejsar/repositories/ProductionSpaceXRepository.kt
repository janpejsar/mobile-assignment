package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXServiceBuilder
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXEndpoints
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.SpaceXRoomDatabase
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductionSpaceXRepository @Inject constructor(private val database: SpaceXRoomDatabase): SpaceXRepository {
    private val service = SpaceXServiceBuilder.buildService(SpaceXEndpoints::class.java)

    override fun getRocketsFromAPI(
        success: (rockets: List<RocketApiEntity>?) -> Unit,
        failure: (t: Throwable?) -> Unit
    ) {
        val call = service.getRockets()
        call.enqueue(object : Callback<List<RocketApiEntity>> {
            override fun onResponse(call: Call<List<RocketApiEntity>>, response: Response<List<RocketApiEntity>>) {
                if (response.isSuccessful) {
                    val rockets = response.body()
                    success(rockets)
                } else {
                    failure(null)
                }
            }

            override fun onFailure(call: Call<List<RocketApiEntity>>, t: Throwable) {
                failure(t)
            }
        })
    }

    override suspend fun saveRocketsToDatabase(
        context: Context,
        rockets: List<RocketApiEntity>
    ) {
        database.rocketDao().insert(rockets.map { rocket -> RocketEntity(rocket) })
    }

    override fun getSavedRocketsLiveData(context: Context): LiveData<List<RocketEntity>> {
        return database.rocketDao().getAll()
    }

    override fun getRocketFromDatabase(
        context: Context,
        rocketId: String
    ): LiveData<RocketEntity> {
        return database.rocketDao().get(rocketId)
    }
}