package cz.quanti.spacexrockets_janpejsar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cz.quanti.spacexrockets_janpejsar.Logger
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: SpaceXRepository
): AndroidViewModel(application) {
    private val subscribers = CompositeDisposable()

    val titleLiveData = MutableLiveData<String>()

    init {
        repository.getRocketsFromAPI()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { rockets ->
                rockets.map { rocket -> RocketEntity(rocket) }
            }
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
            .subscribeBy(
                onSuccess = { rockets ->
                    val builder = StringBuilder()
                    rockets.forEachIndexed { index, rocket -> builder.append("\n${index + 1}.\t${rocket.name} (id: ${rocket.id})") }
                    Logger.i(TAG, "Rockets from API:$builder")

                    repository.saveRocketsToDatabase(getApplication(), rockets)
                },
                onError = {
                    Logger.e(TAG, "Error occurred while fetching data from API (${it.message})", it)
                }
            ).addTo(subscribers)
    }

    override fun onCleared() {
        super.onCleared()
        subscribers.clear()
    }

    companion object {
        private const val TAG = "MainViewModel"
        private const val MAX_RETRIES = 5
        private const val RETRY_SECONDS_MULTIPLIER = 5
    }
}