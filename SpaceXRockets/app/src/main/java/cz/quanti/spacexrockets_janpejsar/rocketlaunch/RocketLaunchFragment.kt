package cz.quanti.spacexrockets_janpejsar.rocketlaunch

import android.animation.ObjectAnimator
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.FragmentRocketLaunchBinding
import kotlin.math.absoluteValue

class RocketLaunchFragment: Fragment(), SensorEventListener {
    private lateinit var binding: FragmentRocketLaunchBinding
    private lateinit var sensorManager: SensorManager
    private var launched = false
    private var wasPutDown = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_launch, container, false)
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.values?.get(1)?.let { y ->
            if (y > 9) {
                launch()
            } else if (y.absoluteValue < 2) {
                wasPutDown = true
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //Ignore
    }

    private fun launch() {
        if (wasPutDown && !launched) {
            Log.d(TAG, "launch: Launched!")

            launched = true
            wasPutDown = false

            binding.rocketImageView.setImageResource(R.drawable.rocket_flying)
            binding.launchInfoTextView.setText(R.string.launch_info_successful)

            val moveBy = binding.rocketLaunchMainLayout.height / 2f + binding.rocketImageView.height
            ObjectAnimator.ofFloat(binding.rocketImageView, "translationY", -moveBy).apply {
                duration = FLIGHT_LENGTH
                start()
            }
        }
    }
    
    companion object {
        private const val TAG = "RocketLaunchFragment"
        private const val FLIGHT_LENGTH = 5000L
    }
}