package cz.quanti.spacexrockets_janpejsar.managers

import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SpaceXSyncManagerModule {

    @Provides
    @Singleton
    fun getSpaceXSyncManager(repository: SpaceXRepository): SpaceXSyncManager {
        return ProductionSpaceXSyncManager(repository)
    }
}