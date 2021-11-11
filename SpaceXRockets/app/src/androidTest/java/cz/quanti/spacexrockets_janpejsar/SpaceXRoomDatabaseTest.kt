package cz.quanti.spacexrockets_janpejsar

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.SpaceXRoomDatabase
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.DimensionEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.MassEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.StageEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class SpaceXRoomDatabaseTest {
    private lateinit var database: SpaceXRoomDatabase
    private lateinit var rocket1a: RocketEntity
    private lateinit var rocket1b: RocketEntity
    private val date = TimeUtils.fromApiStringDate("2021-11-11")

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            SpaceXRoomDatabase::class.java
        ).build()
    }

    @Before
    fun createRockets() {
        rocket1a = RocketEntity(
            "a",
            "Rocket 1a",
            "Rocket number 1, variant A",
            DimensionEntity(10.5f, 100.5f),
            DimensionEntity(20.5f, 200.5f),
            MassEntity(1000, 2000),
            StageEntity(true, 10, 100.5f, 1000),
            StageEntity(false, 20, 200.5f, 0),
            date,
            listOf("https://imgur.com/DaCfMsj.jpg", "https://imgur.com/azYafd8.jpg")
        )
        rocket1b = RocketEntity(
            "a",
            "Rocket 1b",
            "Rocket number 1, variant B",
            DimensionEntity(10.5f, 100.5f),
            DimensionEntity(20.5f, 200.5f),
            MassEntity(1000, 2000),
            StageEntity(true, 10, 100.5f, 1000),
            StageEntity(false, 20, 200.5f, 0),
            date,
            listOf("https://imgur.com/DaCfMsj.jpg", "https://imgur.com/azYafd8.jpg")
        )
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertOneRocket() = runBlocking {
        database.rocketDao().insert(rocket1a)

        val retrieved = database.rocketDao().get(rocket1a.id)

        assertEquals(rocket1a, retrieved)
    }

    @Test
    fun insertTwoWithSameId() = runBlocking {
        database.rocketDao().insert(rocket1a)
        database.rocketDao().insert(rocket1b)

        val retrieved = database.rocketDao().get(rocket1a.id)

        assertEquals(rocket1b, retrieved)
    }
}