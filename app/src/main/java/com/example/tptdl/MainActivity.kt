package com.example.tptdl

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tptdl.weatherAPI.*
import kotlinx.coroutines.*

//import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    private lateinit var connection: ServiceConnection

    var weatherService: WeatherService? = null

    private lateinit var map: MapActivity
    private lateinit var level: LevelActivity
    private lateinit var settings: SettingsActivity

    private lateinit var currentWeather: WeatherState

    // Contains all the views
    //TODO poner bien las bindings para acceder a las views
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askForPermissions()

        val mapButton: Button = findViewById(R.id.map_button)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("weather", currentWeather)
            startActivity(intent)
        }

        val settingsButton: ImageButton = findViewById(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        connection = object : ServiceConnection {
            override fun onServiceDisconnected(componentName: ComponentName) {
                println("Disconnected")
                weatherService = null
            }
            override fun onServiceConnected(componentName: ComponentName, service: IBinder) {
                println("Connected")
                weatherService = (service as WeatherService.WeatherServiceBinder).service

                updateCurrentWeather()
            }
        }

        startService(Intent(this, WeatherService::class.java))

        bindService(Intent(this, WeatherService::class.java),connection, Context.BIND_AUTO_CREATE)
    }

    private fun askForPermissions(){

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    println("Fine permission granted")
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    println("Coarse permission granted")
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_BACKGROUND_LOCATION, false) -> {
                    println("Background permission granted")
                }
                else -> {
                println("No permission granted")
            }
            }
        }

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION))
    }

    private fun updateCurrentWeather(){

        val playButton : Button = findViewById(R.id.play_button)
        val mapButton : Button = findViewById(R.id.map_button)

        playButton.isClickable = false
        mapButton.isClickable = false
        playButton.alpha = 0.5f
        mapButton.alpha = 0.5f

        GlobalScope.launch (Dispatchers.IO) {

            val currentWeatherDeferred = GlobalScope.async { weatherService?.updateWeather() }

            val weatherResponse = currentWeatherDeferred.await()

            currentWeather = if(weatherResponse == null) {
                //TODO Notify user that no weather information is available
                println("no weather information available")
                Normal()
            } else {
                weatherResponse
            }

            playButton.isClickable = true
            mapButton.isClickable = true
            playButton.alpha = 1f
            mapButton.alpha = 1f

            setWeatherIndicator()
        }
    }


    fun setWeatherIndicator() {

        val weatherIcon : ImageView = findViewById(R.id.weatherIcon)
        val weatherText : TextView = findViewById(R.id.weatherText)

        this@MainActivity.runOnUiThread {
            weatherIcon.setImageResource(resources.getIdentifier(currentWeather.toString().lowercase(), "drawable", this.packageName))
            weatherText.text = currentWeather.toString()
        }

    }

    fun clickOnPlayButton(view: View) {
        println(currentWeather)
    }
    /**
     * Called when the user navigates away from the app but might come back
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()
        println("START MAIN")
    }

    override fun onResume() {
        super.onResume()
        println("RESUME MAIN")
    }

    override fun onPause() {
        super.onPause()
        println("PAUSE MAIN")
    }

    override fun onStop() {
        super.onStop()
        println("STOP MAIN")
    }

    override fun onDestroy() {
        println("DESTROY MAIN")
        Toast.makeText(this, "DESTROY MAIN", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        println("RESTART MAIN")
    }

}

