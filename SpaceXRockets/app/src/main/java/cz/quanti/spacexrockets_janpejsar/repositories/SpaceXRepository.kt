package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDatabaseEntity

interface SpaceXRepository {
    fun getRocketsFromAPI(
        success: (rockets: List<RocketApiEntity>?) -> Unit,
        failure: (t: Throwable?) -> Unit
    )

    suspend fun saveRocketsToDatabase(
        context: Context,
        rockets: List<RocketApiEntity>
    )

    fun getSavedRocketsLiveData(
        context: Context
    ): LiveData<List<RocketDatabaseEntity>>

    fun getRocketFromDatabase(
        context: Context,
        rocketId: String
    ): LiveData<RocketDatabaseEntity>
}