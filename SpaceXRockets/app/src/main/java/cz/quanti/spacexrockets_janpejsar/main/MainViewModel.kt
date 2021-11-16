package cz.quanti.spacexrockets_janpejsar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cz.quanti.spacexrockets_janpejsar.Logger
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.StringBuilder
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
            .subscribeBy(
                onNext = { rockets ->
                    val builder = StringBuilder()
                    rockets.forEachIndexed { index, rocket -> builder.append("\n${index + 1}.\t${rocket.name} (id: ${rocket.id})") }
                    Logger.i(TAG, "Rockets from API:$builder")

                    repository.saveRocketsToDatabase(getApplication(), rockets)
                },
                onError = {
                    Logger.w(TAG, "Error occurred while fetching data from API (${it.message})", it)
                },
                onComplete = {
                    Logger.d(TAG, "API request completed")
                }
            ).addTo(subscribers)
    }

    override fun onCleared() {
        super.onCleared()
        subscribers.clear()
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}