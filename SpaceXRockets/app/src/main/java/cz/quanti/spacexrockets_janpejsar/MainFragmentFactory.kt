package cz.quanti.spacexrockets_janpejsar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import cz.quanti.spacexrockets_janpejsar.rocketlaunch.RocketLaunchFragment
import cz.quanti.spacexrockets_janpejsar.rocketlaunch.sensors.RocketSensor
import cz.quanti.spacexrockets_janpejsar.rocketslist.RocketListFragment
import cz.quanti.spacexrockets_janpejsar.rocketslist.RocketsAdapter
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val rocketListAdapter: RocketsAdapter,
    private val rocketSensor: RocketSensor
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        if (className == RocketLaunchFragment::class.java.name) {
            return RocketLaunchFragment(rocketSensor)
        } else if (className == RocketListFragment::class.java.name) {
            return RocketListFragment(rocketListAdapter)
        }

        return super.instantiate(classLoader, className)
    }
}