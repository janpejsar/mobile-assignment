package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity

class FakeSpaceXRepository(
    private val apiList: List<RocketApiEntity>? = mutableListOf(),
    private val databaseList: MutableList<RocketEntity>? = mutableListOf()
): SpaceXRepository {
    override fun getRocketsFromAPI(
        success: (rockets: List<RocketApiEntity>?) -> Unit,
        failure: (t: Throwable?) -> Unit
    ) {
        if (apiList == null) {
            failure(Exception("No rockets"))
        } else {
            success(apiList)
        }
    }

    override suspend fun saveRocketsToDatabase(context: Context, rockets: List<RocketApiEntity>) {
        val list = rockets.map { rocket -> RocketEntity(rocket) } as MutableList
        val add = Array(list.size) {
            return@Array true
        }

        if (databaseList != null) {
            for (i in 0..databaseList.size) {
                for (j in 0..list.size) {
                    if (databaseList[i].id == list[j].id) {
                        databaseList[i] = list[j]
                        add[j] = false
                    }
                }
            }

            for (i in 0..list.size) {
                if (add[i]) {
                    databaseList.add(list[i])
                }
            }
        }
    }

    override fun getSavedRocketsLiveData(context: Context): LiveData<List<RocketEntity>> {
        return MutableLiveData(databaseList)
    }

    override fun getRocketFromDatabase(context: Context, rocketId: String): LiveData<RocketEntity> {
        databaseList?.forEach {
            if (it.id == rocketId) {
                return MutableLiveData(it)
            }
        }

        return MutableLiveData()
    }
}