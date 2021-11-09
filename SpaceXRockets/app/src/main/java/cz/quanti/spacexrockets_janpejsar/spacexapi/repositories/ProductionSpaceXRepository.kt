package cz.quanti.spacexrockets_janpejsar.spacexapi.repositories

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXServiceBuilder
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXEndpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductionSpaceXRepository @Inject constructor(): SpaceXRepository {
    private val service = SpaceXServiceBuilder.buildService(SpaceXEndpoints::class.java)

    override fun getRockets(success: (rockets: List<Rocket>?) -> Unit,
                            failure: (t: Throwable?) -> Unit) {
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
}