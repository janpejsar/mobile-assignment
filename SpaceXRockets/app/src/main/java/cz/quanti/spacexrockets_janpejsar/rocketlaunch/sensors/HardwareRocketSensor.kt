package cz.quanti.spacexrockets_janpejsar.rocketlaunch.sensors

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import cz.quanti.spacexrockets_janpejsar.rocketlaunch.RocketFlightState
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.AbstractMap
import kotlin.math.absoluteValue

class HardwareRocketSensor(
    private val activity: Activity
): RocketSensor, SensorEventListener {
    private var sensorManager: SensorManager? = null
    private val xObservable = PublishSubject.create<Float>()
    private val stateObservable: BehaviorSubject<RocketFlightState> = BehaviorSubject.create()

    init {
        stateObservable.startWithItem(RocketFlightState.READY)

        Observable.combineLatest(stateObservable, xObservable, { state, x ->
            AbstractMap.SimpleEntry(state, x)
        }).toFlowable(BackpressureStrategy.DROP)
            .subscribe {
                stateOrXChanged(it.key, it.value)
            }

        stateObservable.onNext(RocketFlightState.READY)
    }

    override fun getStateObservable(): Observable<RocketFlightState> {
        return stateObservable
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.values?.get(1)?.let { x ->
            xObservable.onNext(x)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //Ignore
    }

    override fun resume() {
        getSensorManager().getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { sensor ->
            getSensorManager().registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun pause() {
        getSensorManager().unregisterListener(this)
    }

    override fun animationFinished() {
        stateObservable.onNext(RocketFlightState.COMPLETE)
    }

    private fun getSensorManager(): SensorManager {
        if (sensorManager == null) {
            sensorManager = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        }

        return sensorManager as SensorManager
    }

    private fun stateOrXChanged(state: RocketFlightState, x: Float) {
        if (x > 9) {
            if (state == RocketFlightState.READY) {
                launch()
            }
        } else if (x.absoluteValue < 2) {
            if (state == RocketFlightState.COMPLETE) {
                resetMinigame()
            }
        }
    }

    private fun resetMinigame() {
        stateObservable.onNext(RocketFlightState.READY)
    }

    private fun launch() {
        stateObservable.onNext(RocketFlightState.FLYING)
    }
}