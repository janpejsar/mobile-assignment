package cz.quanti.spacexrockets_janpejsar.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.MaterialToolbar
import cz.quanti.spacexrockets_janpejsar.NavigationActivity
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.ActivityMainBinding
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : NavigationActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var repository: SpaceXRepository

    override fun onCreateBeforeNavigationSetup(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.titleLiveData.observe(this, {
            if (it != null && it.isNotEmpty()) {
                binding.mainToolbar.title = it
            }
        })
    }

    override fun getToolbar(): MaterialToolbar {
        return binding.mainToolbar
    }

    override fun getNavHostFragment(): View {
        return findViewById(R.id.navHost)
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        viewModel.titleLiveData.value = title?.toString()
    }

    override fun setTitle(titleId: Int) {
        super.setTitle(titleId)
        viewModel.titleLiveData.value = resources.getString(titleId)
    }
}