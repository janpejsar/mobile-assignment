package cz.quanti.spacexrockets_janpejsar.rocketlaunch

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cz.quanti.spacexrockets_janpejsar.R
import kotlin.math.absoluteValue

class RocketSensor(
    activity: Activity,
    private val imageView: ImageView,
    private val textView: TextView,
    private val root: View
): SensorEventListener {
    private var sensorManager: SensorManager = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var launched = false
    private var wasPutDown = true

    override fun onSensorChanged(event: SensorEvent?) {
        event?.values?.get(1)?.let { y ->
            if (y > 9) {
                launch()
            } else if (y.absoluteValue < 2) {
                wasPutDown = true

                if (!launched && imageView.translationY != 0f) {
                    resetMinigame()
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //Ignore
    }

    fun resume() {
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    fun pause() {
        sensorManager.unregisterListener(this)
    }

    private fun resetMinigame() {
        imageView.translationY = 0f
        imageView.setImageResource(R.drawable.rocket_idle)
        textView.setText(R.string.launch_info_move_phone)
    }

    private fun launch() {
        if (wasPutDown && !launched) {
            Log.d(TAG, "launch: Launched!")

            launched = true
            wasPutDown = false

            imageView.setImageResource(R.drawable.rocket_flying)
            textView.setText(R.string.launch_info_successful)

            val moveBy = root.height / 2f + imageView.height

            RocketAnimation.animate(imageView, FLIGHT_LENGTH, moveBy) {
                launched = false
                Log.d(TAG, "launch: Flight complete")
            }
        }
    }

    companion object {
        private const val TAG = "RocketStartSensor"
        private const val FLIGHT_LENGTH = 5000L
    }
}