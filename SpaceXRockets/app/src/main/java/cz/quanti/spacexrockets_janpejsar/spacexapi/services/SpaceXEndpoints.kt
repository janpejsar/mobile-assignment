package cz.quanti.spacexrockets_janpejsar.spacexapi.services

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import retrofit2.Call
import retrofit2.http.GET

interface SpaceXEndpoints {
    @GET("rockets")
    fun getRockets(): Call<List<RocketApiEntity>>
}