package cz.quanti.spacexrockets_janpejsar.rocketlaunch

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import cz.quanti.spacexrockets_janpejsar.rocketlaunch.sensors.RocketSensor
import javax.inject.Inject

class RocketLaunchFragmentFactory @Inject constructor(
    private val rocketSensor: RocketSensor
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        if (className == RocketLaunchFragment::class.java.name) {
            return RocketLaunchFragment(rocketSensor)
        }

        return super.instantiate(classLoader, className)
    }
}