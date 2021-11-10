package cz.quanti.spacexrockets_janpejsar.repositories

import android.content.Context
import androidx.room.Room
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXEndpoints
import cz.quanti.spacexrockets_janpejsar.spacexapi.services.SpaceXServiceBuilder
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.SpaceXRoomDatabase
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.daos.RocketDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SpaceXRepositoryModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SpaceXRoomDatabase {
        return Room.databaseBuilder(context, SpaceXRoomDatabase::class.java, SpaceXRoomDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideRocketDao(database: SpaceXRoomDatabase): RocketDao {
        return database.rocketDao()
    }

    @Provides
    @Singleton
    fun provideEndpoints(): SpaceXEndpoints {
        return SpaceXServiceBuilder.buildService(SpaceXEndpoints::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(database: SpaceXRoomDatabase, endpoints: SpaceXEndpoints): SpaceXRepository {
        return ProductionSpaceXRepository(database, endpoints)
    }
}