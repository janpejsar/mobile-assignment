package cz.quanti.spacexrockets_janpejsar.rocketslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RocketListViewModel @Inject constructor(
    application: Application,
    private val repository: SpaceXRepository
): AndroidViewModel(application) {

    var rocketsLiveData: LiveData<List<RocketItem>> = mapRocketsToRocketItems()

    private fun mapRocketsToRocketItems(): LiveData<List<RocketItem>> {
        return Transformations.map(repository.getSavedRocketsLiveData(getApplication())) { rockets ->
            rockets.map { rocket ->
                RocketItem(rocket)
            }
        }
    }
}