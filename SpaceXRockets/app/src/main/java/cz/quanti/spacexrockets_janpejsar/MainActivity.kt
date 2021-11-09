package cz.quanti.spacexrockets_janpejsar

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.MaterialToolbar
import cz.quanti.spacexrockets_janpejsar.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : NavigationActivity() {
    private lateinit var binding: ActivityMainBinding

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