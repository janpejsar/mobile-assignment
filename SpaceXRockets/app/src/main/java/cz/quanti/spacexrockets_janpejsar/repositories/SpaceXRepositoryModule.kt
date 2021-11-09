package cz.quanti.spacexrockets_janpejsar.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface SpaceXRepositoryModule {
    @Binds
    fun provideProductionSpaceXRepository(repository: ProductionSpaceXRepository): SpaceXRepository
}