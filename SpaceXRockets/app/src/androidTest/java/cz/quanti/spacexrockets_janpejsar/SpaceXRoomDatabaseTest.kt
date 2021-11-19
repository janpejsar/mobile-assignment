package cz.quanti.spacexrockets_janpejsar

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.SpaceXRoomDatabase
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.DimensionDbEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.MassDbEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDbEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.StageDbEntity
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
    private lateinit var rocket1a: RocketDbEntity
    private lateinit var rocket1b: RocketDbEntity
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
        rocket1a = RocketDbEntity(
            "a",
            "Rocket 1a",
            "Rocket number 1, variant A",
            DimensionDbEntity(10.5f, 100.5f),
            DimensionDbEntity(20.5f, 200.5f),
            MassDbEntity(1000, 2000),
            StageDbEntity(true, 10, 100.5f, 1000),
            StageDbEntity(false, 20, 200.5f, 0),
            date,
            listOf("https://imgur.com/DaCfMsj.jpg", "https://imgur.com/azYafd8.jpg")
        )
        rocket1b = RocketDbEntity(
            "a",
            "Rocket 1b",
            "Rocket number 1, variant B",
            DimensionDbEntity(10.5f, 100.5f),
            DimensionDbEntity(20.5f, 200.5f),
            MassDbEntity(1000, 2000),
            StageDbEntity(true, 10, 100.5f, 1000),
            StageDbEntity(false, 20, 200.5f, 0),
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