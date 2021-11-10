package cz.quanti.spacexrockets_janpejsar.rocketdetail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.DimensionEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.MassEntity
import kotlin.math.roundToInt

object BindingAdapters {
    @BindingAdapter("dimension")
    @JvmStatic
    fun setDimension(view: TextView, dimension: DimensionEntity?) {
        view.text = if (dimension == null) {
            null
        } else {
            view.context.resources.getString(
                R.string.dimension_meters,
                dimension.meters.roundToInt()
            )
        }
    }

    @BindingAdapter("mass")
    @JvmStatic
    fun setMass(view: TextView, mass: MassEntity?) {
        view.text = if (mass == null) {
            null
        } else {
            view.context.resources.getString(
                R.string.mass_tons,
                mass.kg / 1000
            )
        }
    }

    @BindingAdapter("reusable")
    @JvmStatic
    fun reusable(view: TextView, reusable: Boolean?) {
        view.setText(
            if (reusable != null && reusable) {
                R.string.reusable
            } else {
                R.string.not_reusable
            }
        )
    }
}