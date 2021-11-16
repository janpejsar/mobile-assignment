package cz.quanti.spacexrockets_janpejsar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cz.quanti.spacexrockets_janpejsar.Logger
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: SpaceXRepository
): AndroidViewModel(application) {
    val titleLiveData = MutableLiveData<String>()

    init {
        repository.getRocketsFromAPI()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { rockets ->
                val builder = StringBuilder("Rocket list:")
                rockets.forEachIndexed { index, rocket -> builder.append("\n${index + 1}.\t${rocket.name}") }
                Logger.i(TAG, "printRockets:\n$builder")

                repository.saveRocketsToDatabase(getApplication(), rockets)
            }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}