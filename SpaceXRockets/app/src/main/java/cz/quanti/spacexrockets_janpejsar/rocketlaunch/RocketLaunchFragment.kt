package cz.quanti.spacexrockets_janpejsar.rocketlaunch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cz.quanti.spacexrockets_janpejsar.utils.Logger
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.FragmentRocketLaunchBinding
import cz.quanti.spacexrockets_janpejsar.rocketlaunch.sensors.RocketSensor
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

@AndroidEntryPoint
class RocketLaunchFragment @Inject constructor(private  val rocketSensor: RocketSensor): Fragment() {
    private lateinit var binding: FragmentRocketLaunchBinding
    private lateinit var subscribers: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subscribers = CompositeDisposable()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_launch, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setupRocketSensor()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        Logger.d(TAG, "onResume: ${viewLifecycleOwner.lifecycle.currentState.name}")
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
        rocketSensor.getStateObservable().subscribe {
            Logger.d(TAG, "Flight state changed to: $it")

            if (it == RocketFlightState.READY) {
                binding.rocketImageView.translationY = 0F
            } else if (it == RocketFlightState.FLYING) {
                startAnimation()
            }

            changeImageAndText(it)
        }.addTo(subscribers)
    }

    private fun startAnimation() {
        val moveBy = binding.root.height / 2f + binding.rocketImageView.height

        RocketAnimation.animate(binding.rocketImageView, moveBy) {
            rocketSensor.animationFinished()
        }
    }

    private fun changeImageAndText(state: RocketFlightState) {
        if (state == RocketFlightState.READY) {
            binding.rocketImageView.setImageResource(R.drawable.rocket_idle)
            binding.launchInfoTextView.setText(R.string.launch_info_move_phone)
        } else if (state == RocketFlightState.FLYING) {
            binding.rocketImageView.setImageResource(R.drawable.rocket_flying)
            binding.launchInfoTextView.setText(R.string.launch_info_successful)
        }
    }

    companion object {
        private const val TAG = "RocketLaunchFragment"
    }
}