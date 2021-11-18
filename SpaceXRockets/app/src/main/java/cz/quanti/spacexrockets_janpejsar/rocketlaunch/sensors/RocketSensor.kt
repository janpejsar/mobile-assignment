package cz.quanti.spacexrockets_janpejsar.rocketlaunch.sensors

import cz.quanti.spacexrockets_janpejsar.rocketlaunch.RocketFlightState
import io.reactivex.rxjava3.core.Observable

interface RocketSensor {
    fun resume()
    fun pause()
    fun animationFinished()
    fun getStateObservable(): Observable<RocketFlightState>
}