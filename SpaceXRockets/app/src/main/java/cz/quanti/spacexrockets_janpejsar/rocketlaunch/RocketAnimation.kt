package cz.quanti.spacexrockets_janpejsar.rocketlaunch

import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.doOnEnd

object RocketAnimation {
    private const val FLIGHT_LENGTH = 5000L

    fun animate(
        view: View,
        moveBy: Float,
        doOnEnd: () -> Unit
    ) {
        ObjectAnimator.ofFloat(view, "translationY", -moveBy).apply {
            this.duration = FLIGHT_LENGTH
            start()

            doOnEnd {
                doOnEnd()
            }
        }
    }
}