package cz.quanti.spacexrockets_janpejsar.services

import cz.quanti.spacexrockets_janpejsar.entities.Rocket
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface SpaceXEndpoints {
    @GET("rockets")
    fun getRockets(): Call<List<Rocket>>
}