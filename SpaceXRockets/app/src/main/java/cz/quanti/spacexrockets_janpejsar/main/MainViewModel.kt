package cz.quanti.spacexrockets_janpejsar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.RocketApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: SpaceXRepository
): AndroidViewModel(application) {
    val titleLiveData = MutableLiveData<String>()

    init {
        repository.getRocketsFromAPI(this::success, this::failure)
    }

    private fun success(rockets: List<RocketApiEntity>?) {
        Log.i(TAG, "onResponse: Success! :) Got ${rockets?.size} rockets")

        if (rockets != null) {
            val builder = StringBuilder("Rocket list:")
            rockets.forEachIndexed { index, rocket -> builder.append("\n${index + 1}.\t${rocket.name}") }
            Log.i(TAG, "printRockets: $builder")

            viewModelScope.launch(Dispatchers.IO) {
                repository.saveRocketsToDatabase(
                    getApplication(),
                    rockets
                )
            }
        }
    }

    private fun failure(t: Throwable?) {
        Log.e(TAG, "failure: Failed", t)
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}