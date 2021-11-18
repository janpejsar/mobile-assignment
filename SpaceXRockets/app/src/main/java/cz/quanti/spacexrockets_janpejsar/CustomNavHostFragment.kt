package cz.quanti.spacexrockets_janpejsar

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import cz.quanti.spacexrockets_janpejsar.rocketlaunch.RocketLaunchFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomNavHostFragment: NavHostFragment() {
    @Inject
    lateinit var fragmentFactory: RocketLaunchFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = fragmentFactory
    }
}