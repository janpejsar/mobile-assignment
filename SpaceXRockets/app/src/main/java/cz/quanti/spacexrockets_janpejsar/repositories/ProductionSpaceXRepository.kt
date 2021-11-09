package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXServiceBuilder
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXEndpoints
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.SpaceXRoomDatabase
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDatabaseEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductionSpaceXRepository @Inject constructor(): SpaceXRepository {
    private val service = SpaceXServiceBuilder.buildService(SpaceXEndpoints::class.java)

    override fun getRocketsFromAPI(
        success: (rockets: List<Rocket>?) -> Unit,
        failure: (t: Throwable?) -> Unit
    ) {
        val call = service.getRockets()
        call.enqueue(object : Callback<List<Rocket>> {
            override fun onResponse(call: Call<List<Rocket>>, response: Response<List<Rocket>>) {
                if (response.isSuccessful) {
                    val rockets = response.body()
                    success(rockets)
                } else {
                    failure(null)
                }
            }

            override fun onFailure(call: Call<List<Rocket>>, t: Throwable) {
                failure(t)
            }
        })
    }

    override suspend fun saveRocketsToDatabase(
        context: Context,
        rockets: List<Rocket>
    ) {
        val database = SpaceXRoomDatabase.getDatabase(context)
        val dbe = ArrayList<RocketDatabaseEntity>()

        rockets.forEach {
            dbe.add(RocketDatabaseEntity(it))
        }

        database.rocketDao().insert(dbe)
    }

    override fun getAllRocketsFromDatabase(context: Context): LiveData<List<RocketDatabaseEntity>> {
        val database = SpaceXRoomDatabase.getDatabase(context)
        return database.rocketDao().getAll()
    }
}