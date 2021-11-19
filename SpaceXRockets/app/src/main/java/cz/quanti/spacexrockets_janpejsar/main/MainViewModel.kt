package cz.quanti.spacexrockets_janpejsar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application
): AndroidViewModel(application) {
    private val subscribers = CompositeDisposable()

    val titleLiveData = MutableLiveData<String>()

    init {

    }

    override fun onCleared() {
        super.onCleared()
        subscribers.clear()
    }


}