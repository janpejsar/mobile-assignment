package cz.quanti.spacexrockets_janpejsar.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class, ActivityComponent::class)
abstract class SpaceXRepositoryModule {
    @Binds
    abstract fun provideProductionSpaceXRepository(repository: ProductionSpaceXRepository): SpaceXRepository
}