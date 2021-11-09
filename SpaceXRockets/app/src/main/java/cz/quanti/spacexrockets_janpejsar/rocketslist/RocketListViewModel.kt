package cz.quanti.spacexrockets_janpejsar.rocketslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDatabaseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RocketListViewModel @Inject constructor(application: Application, repository: SpaceXRepository): AndroidViewModel(application) {
    var rocketsLiveData: LiveData<List<RocketDatabaseEntity>> = repository.getAllRocketsFromDatabase(application)
}