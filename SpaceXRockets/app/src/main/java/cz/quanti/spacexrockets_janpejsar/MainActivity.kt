package cz.quanti.spacexrockets_janpejsar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cz.quanti.spacexrockets_janpejsar.entities.Rocket
import cz.quanti.spacexrockets_janpejsar.repositories.SpaceXRepository
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = SpaceXRepository()
        repository.getRockets(this::success, this::failure)
    }

    private fun success(rockets: List<Rocket>?) {
        Log.i(TAG, "onResponse: Success! :) Got ${rockets?.size} rockets")

        if (rockets != null) {
            val builder = StringBuilder("Rocket list:")
            rockets.forEachIndexed { index, rocket -> builder.append("\n${index + 1}.\t${rocket.rocketName}") }
            Log.i(TAG, "printRockets: $builder")
        }
    }

    private fun failure(t: Throwable?) {
        Log.e(TAG, "failure: Failed", t)
    }
}