package cz.quanti.spacexrockets_janpejsar.rocketdetail

import android.util.Log
import android.util.Patterns
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.entities.Dimension
import cz.quanti.spacexrockets_janpejsar.entities.Mass
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.DimensionDbEntity
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.MassDbEntity
import kotlin.math.roundToInt

object BindingAdapters {
    private const val TAG = "BindingAdapters"

    @BindingAdapter("dimension")
    @JvmStatic
    fun setDimension(view: TextView, dimension: Dimension?) {
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
    fun setMass(view: TextView, mass: Mass?) {
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
    fun setReusable(view: TextView, reusable: Boolean?) {
        view.setText(
            if (reusable != null && reusable) {
                R.string.reusable
            } else {
                R.string.not_reusable
            }
        )
    }

    @BindingAdapter("imageURL")
    @JvmStatic
    fun setImageURL(view: ImageView, url: String?) {
        if (url != null && url.isNotBlank() && Patterns.WEB_URL.matcher(url).matches()) {
            Picasso.get()
                .load(url)
                .into(view)
        } else {
            Log.i(TAG, "setImageURL: Invalid URL: $url")
        }
    }
}