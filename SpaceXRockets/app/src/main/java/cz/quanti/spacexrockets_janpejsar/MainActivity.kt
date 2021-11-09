package cz.quanti.spacexrockets_janpejsar

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.MaterialToolbar
import cz.quanti.spacexrockets_janpejsar.databinding.ActivityMainBinding
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : NavigationActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: SpaceXRepository

    override fun onCreateBeforeNavigationSetup(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun getToolbar(): MaterialToolbar {
        return binding.mainToolbar
    }

    override fun getNavHostFragment(): View {
        return findViewById(R.id.navHost)
    }
}