package cz.quanti.spacexrockets_janpejsar.rocketlaunch

import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.doOnEnd

object RocketAnimation {
    fun animate(
        view: View,
        duration: Long,
        moveBy: Float,
        doOnEnd: () -> Unit
    ) {
        ObjectAnimator.ofFloat(view, "translationY", -moveBy).apply {
            this.duration = duration
            start()

            doOnEnd {
                doOnEnd()
            }
        }
    }
}