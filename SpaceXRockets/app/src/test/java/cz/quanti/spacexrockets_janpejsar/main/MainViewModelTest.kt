package cz.quanti.spacexrockets_janpejsar.main

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cz.quanti.spacexrockets_janpejsar.repositories.FakeSpaceXRepository
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    @Test
    fun getRocketsFromApi_success() {
        val repository = FakeSpaceXRepository()
        val viewModel = MainViewModel(ApplicationProvider.getApplicationContext(), repository)

        //TODO(Něco tady vymyslet)
        assert(true)
    }
}