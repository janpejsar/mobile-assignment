package cz.quanti.spacexrockets_janpejsar.spacexapi.services

import com.squareup.moshi.Moshi
import cz.quanti.spacexrockets_janpejsar.spacexapi.ApiDateAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object SpaceXServiceBuilder {
    private val moshi = Moshi.Builder()
        .add(ApiDateAdapter())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/v4/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}