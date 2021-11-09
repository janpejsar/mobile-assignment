package cz.quanti.spacexrockets_janpejsar.rocketslist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket
import cz.quanti.spacexrockets_janpejsar.spacexapi.repositories.ProductionSpaceXRepository
import java.lang.StringBuilder

class RocketListViewModel(application: Application): AndroidViewModel(application) {
    private val _rocketsLiveData = MutableLiveData<List<Rocket>>()
    val rocketsLiveData: LiveData<List<Rocket>> by ::_rocketsLiveData

    init {
        val repository = ProductionSpaceXRepository()
        repository.getRockets(this::success, this::failure)
    }

    private fun success(rockets: List<Rocket>?) {
        Log.i(TAG, "onResponse: Success! :) Got ${rockets?.size} rockets")
        _rocketsLiveData.value = rockets

        if (rockets != null) {
            val builder = StringBuilder("Rocket list:")
            rockets.forEachIndexed { index, rocket -> builder.append("\n${index + 1}.\t${rocket.name}") }
            Log.i(TAG, "printRockets: $builder")
        }
    }

    private fun failure(t: Throwable?) {
        Log.e(TAG, "failure: Failed", t)
        _rocketsLiveData.value = null
    }

    companion object {
        private const val TAG = "RocketListViewModel"
    }
}