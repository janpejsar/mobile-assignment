package cz.quanti.spacexrockets_janpejsar

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.MaterialToolbar
import cz.quanti.spacexrockets_janpejsar.databinding.ActivityMainBinding
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : NavigationActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: SpaceXRepository

    override fun onCreateBeforeNavigationSetup(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        repository.getRocketsFromAPI(this::success, this::failure)
    }

    override fun getToolbar(): MaterialToolbar {
        return binding.mainToolbar
    }

    override fun getNavHostFragment(): View {
        return findViewById(R.id.navHost)
    }

    private fun success(rockets: List<Rocket>?) {
        Log.i(TAG, "onResponse: Success! :) Got ${rockets?.size} rockets")

        if (rockets != null) {
            val builder = StringBuilder("Rocket list:")
            rockets.forEachIndexed { index, rocket -> builder.append("\n${index + 1}.\t${rocket.name}") }
            Log.i(TAG, "printRockets: $builder")

             lifecycleScope.launch(Dispatchers.IO) {
                repository.saveRocketsToDatabase(
                    this@MainActivity,
                    rockets
                )
            }
        }
    }

    private fun failure(t: Throwable?) {
        Log.e(TAG, "failure: Failed", t)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}