package cz.quanti.spacexrockets_janpejsar.rocketlaunch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.FragmentRocketLaunchBinding

class RocketLaunchFragment: Fragment() {
    private lateinit var binding: FragmentRocketLaunchBinding
    private lateinit var rocketSensor: RocketSensor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_launch, container, false)
        rocketSensor = RocketSensor(
            requireActivity(),
            binding.rocketImageView,
            binding.launchInfoTextView,
            binding.rocketLaunchMainLayout
        )

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        rocketSensor.resume()
    }

    override fun onPause() {
        super.onPause()
        rocketSensor.pause()
    }
}