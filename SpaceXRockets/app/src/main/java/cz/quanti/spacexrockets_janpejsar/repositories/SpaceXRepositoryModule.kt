package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import androidx.room.Room
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.DATABASE_NAME
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.SpaceXRoomDatabase
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos.RocketDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class, ActivityComponent::class)
abstract class SpaceXRepositoryModule {
    @Binds
    abstract fun provideProductionSpaceXRepository(repository: ProductionSpaceXRepository): SpaceXRepository

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): SpaceXRoomDatabase {
        return Room.databaseBuilder(context, SpaceXRoomDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideRocketDao(db: SpaceXRoomDatabase): RocketDao {
        return db.rocketDao()
    }
}