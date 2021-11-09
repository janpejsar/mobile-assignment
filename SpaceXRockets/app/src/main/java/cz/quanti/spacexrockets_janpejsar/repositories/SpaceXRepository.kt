package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket

interface SpaceXRepository {
    fun getRocketsFromAPI(
        success: (rockets: List<Rocket>?) -> Unit,
        failure: (t: Throwable?) -> Unit
    )

    suspend fun saveRocketsToDatabase(
        context: Context,
        rockets: List<Rocket>
    )
}