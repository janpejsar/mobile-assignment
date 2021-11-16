package cz.quanti.spacexrockets_janpejsar.spacexapi.services

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface SpaceXEndpoints {
    @GET("rockets")
    fun getRockets(): Single<List<RocketApiEntity>>
}