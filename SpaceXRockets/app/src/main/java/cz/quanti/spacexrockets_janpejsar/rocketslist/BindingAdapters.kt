package cz.quanti.spacexrockets_janpejsar.rocketslist

import android.widget.TextView
import androidx.databinding.BindingAdapter
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.utils.TimeUtils
import java.util.*

object BindingAdapters {
    @BindingAdapter("app:firstFlight")
    @JvmStatic
    fun setFirstFlight(view: TextView, date: Date) {
        view.text = view.context.resources.getString(R.string.first_flight, TimeUtils.toStringDate(date))
    }
}