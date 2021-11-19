package cz.quanti.spacexrockets_janpejsar.rocketslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RocketListViewModel @Inject constructor(
    application: Application,
    private val repository: SpaceXRepository
): AndroidViewModel(application) {
    private val _rocketsLiveData = MutableLiveData<List<RocketItem>>()
    val rocketsLiveData: LiveData<List<RocketItem>> by ::_rocketsLiveData

    init {
        mapRocketsToRocketItems()
    }

    private fun mapRocketsToRocketItems() {
        repository.getSavedRocketsObservable()
            .map { rockets ->
                rockets.map { RocketItem(it) }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _rocketsLiveData.value = it
            }
    }
}