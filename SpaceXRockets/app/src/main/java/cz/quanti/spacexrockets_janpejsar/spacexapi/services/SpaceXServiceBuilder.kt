package cz.quanti.spacexrockets_janpejsar.spacexapi.services

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object SpaceXServiceBuilder {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/v4/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}