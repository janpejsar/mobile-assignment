package cz.quanti.spacexrockets_janpejsar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cz.quanti.spacexrockets_janpejsar.entities.Rocket
import cz.quanti.spacexrockets_janpejsar.services.ServiceBuilder
import cz.quanti.spacexrockets_janpejsar.services.SpaceXEndpoints
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = ServiceBuilder.buildService(SpaceXEndpoints::class.java)
        val call = service.getRockets()

        /*call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse: Success! :)\n${response.body()?.string()}")
                } else {
                    Log.w(TAG, "onResponse: Not successful: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

        })*/

        call.enqueue(object : Callback<List<Rocket>> {
            override fun onResponse(call: Call<List<Rocket>>, response: Response<List<Rocket>>) {
                if (response.isSuccessful) {
                    val rockets = response.body()

                    Log.i(TAG, "onResponse: Success! :) Got ${rockets?.size} rockets")

                    if (rockets != null) {
                        printRockets(rockets)
                    }
                } else {
                    Log.w(TAG, "onResponse: Not successful: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Rocket>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }
        })
    }

    private fun printRockets(rockets: List<Rocket>) {
        val builder = StringBuilder("Rocket list:")
        rockets.forEachIndexed { index, rocket -> builder.append("\n${index + 1}.\t${rocket.rocketName}") }
        Log.i(TAG, "printRockets: $builder")
    }
}