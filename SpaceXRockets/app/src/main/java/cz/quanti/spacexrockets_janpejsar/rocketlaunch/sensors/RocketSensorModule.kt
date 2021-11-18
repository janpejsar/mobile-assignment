package cz.quanti.spacexrockets_janpejsar.rocketlaunch.sensors

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
@Module
@InstallIn(ActivityComponent::class)
class RocketSensorModule {
    @Provides
    @ActivityScoped
    fun provideRocketSensor(@ActivityContext context: Context): RocketSensor {
        return HardwareRocketSensor(context as Activity)
    }
}