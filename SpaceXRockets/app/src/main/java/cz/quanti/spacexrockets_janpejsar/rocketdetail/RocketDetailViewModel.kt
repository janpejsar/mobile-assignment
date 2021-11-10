package cz.quanti.spacexrockets_janpejsar.rocketdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RocketDetailViewModel @Inject constructor(application: Application, private val repository: SpaceXRepository): AndroidViewModel(application) {
    var rocketLiveData: LiveData<RocketEntity> = MutableLiveData()

    fun loadRocket(rocketId: String?) {
        if (rocketId != null) {
            rocketLiveData = repository.getRocketFromDatabase(getApplication(), rocketId)
        }
    }
}