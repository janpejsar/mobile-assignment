package cz.quanti.spacexrockets_janpejsar.managers

import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import cz.quanti.spacexrockets_janpejsar.utils.Logger
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductionSpaceXSyncManager @Inject constructor(
    private val repository: SpaceXRepository
): SpaceXSyncManager {
    override fun sync() {
        repository.getRocketsFromAPI()
            .retryWhen { errors: Flowable<Throwable> ->
                errors.scan(0) { count, error ->
                    if (count > MAX_RETRIES) {
                        throw error
                    }

                    count + 1
                }.flatMap {
                    if (it > 0) {
                        val seconds = it.toLong() * RETRY_SECONDS_MULTIPLIER
                        Logger.w(TAG, "Error occurred, retrying in $seconds seconds")
                        Flowable.timer(seconds, TimeUnit.SECONDS)
                    } else {
                        Flowable.empty()
                    }
                }
            }
            .flatMapCompletable { rockets ->
                val builder = StringBuilder()
                rockets.forEachIndexed { index, rocket -> builder.append("\n${index + 1}.\t${rocket.name} (id: ${rocket.id})") }
                Logger.d(TAG, "Rockets from API:$builder")

                repository.saveRocketsToDatabase(rockets)
            }
            .subscribeBy(
                onComplete = {
                     Logger.i(TAG, "Rockets successfully downloaded and saved")
                },
                onError = {
                    Logger.e(TAG, "Error occurred while fetching data from API (${it.message})", it)
                }
            )
    }

    companion object {
        private const val TAG = "SpaceXSyncManager"
        private const val MAX_RETRIES = 5
        private const val RETRY_SECONDS_MULTIPLIER = 5
    }
}