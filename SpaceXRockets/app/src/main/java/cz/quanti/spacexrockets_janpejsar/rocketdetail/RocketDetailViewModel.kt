package cz.quanti.spacexrockets_janpejsar.rocketdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.quanti.spacexrockets_janpejsar.entities.Rocket
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RocketDetailViewModel @Inject constructor(application: Application, private val repository: SpaceXRepository): AndroidViewModel(application) {
    private val _rocketLiveData = MutableLiveData<Rocket>()
    val rocketLiveData: LiveData<Rocket> by ::_rocketLiveData

    fun loadRocket(rocketId: String?) {
        if (rocketId != null) {
            repository.getRocketFromDatabase(getApplication(), rocketId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _rocketLiveData.value = it
                }
        }
    }
}