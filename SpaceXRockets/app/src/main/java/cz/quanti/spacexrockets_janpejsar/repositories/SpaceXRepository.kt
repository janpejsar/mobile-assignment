package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDatabaseEntity

interface SpaceXRepository {
    fun getRocketsFromAPI(
        success: (rockets: List<Rocket>?) -> Unit,
        failure: (t: Throwable?) -> Unit
    )

    suspend fun saveRocketsToDatabase(
        context: Context,
        rockets: List<Rocket>
    )

    fun getAllRocketsFromDatabase(
        context: Context
    ): LiveData<List<RocketDatabaseEntity>>
}