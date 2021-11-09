package cz.quanti.spacexrockets_janpejsar.rocketdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RocketDetailViewModel @Inject constructor(application: Application): AndroidViewModel(application) {
}