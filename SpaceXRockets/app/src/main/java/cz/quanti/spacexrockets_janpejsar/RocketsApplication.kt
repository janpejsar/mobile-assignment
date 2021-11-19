package cz.quanti.spacexrockets_janpejsar

import android.app.Application
import cz.quanti.spacexrockets_janpejsar.managers.SpaceXSyncManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class RocketsApplication @Inject constructor(
): Application() {
    @Inject
    lateinit var syncManager: SpaceXSyncManager

    override fun onCreate() {
        super.onCreate()
        syncManager.sync()
    }
}