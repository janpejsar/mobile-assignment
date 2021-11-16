package cz.quanti.spacexrockets_janpejsar.rocketlaunch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cz.quanti.spacexrockets_janpejsar.Logger
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.FragmentRocketLaunchBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

class RocketLaunchFragment: Fragment() {
    private lateinit var binding: FragmentRocketLaunchBinding
    private lateinit var rocketSensor: RocketSensor
    private lateinit var subscribers: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subscribers = CompositeDisposable()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_launch, container, false)

        setupRocketSensor()
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

    override fun onDestroyView() {
        super.onDestroyView()
        subscribers.dispose()
    }

    private fun setupRocketSensor() {
        rocketSensor = RocketSensor(
            requireActivity(),
            binding.rocketImageView,
            binding.rocketLaunchMainLayout
        )

        rocketSensor.imageObservable.subscribe {
            binding.rocketImageView.setImageResource(it)
        }.addTo(subscribers)
        rocketSensor.textObservable.subscribe {
            binding.launchInfoTextView.setText(it)
        }.addTo(subscribers)
        rocketSensor.stateObservable.subscribe {
            Logger.d(TAG, "setupRocketSensor: $it")
            if (it == RocketFlightState.READY) {
                binding.rocketImageView.translationY = 0F
            }
        }.addTo(subscribers)
    }

    companion object {
        private const val TAG = "RocketLaunchFragment"
    }
}