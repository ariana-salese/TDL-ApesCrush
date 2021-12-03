package com.example.tptdl

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.text.Layout
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.example.tptdl.weatherAPI.*
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

//import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback,
    AdapterView.OnItemSelectedListener {

    private lateinit var connection: ServiceConnection

    var weatherService: WeatherService? = null
    var isCheckingForPermissions = false
    private lateinit var currentWeather: WeatherState

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        askForForegroundPermissions()

        val levelButton: Button = findViewById(R.id.play_button)
        levelButton.setOnClickListener {
            val intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("weather", currentWeather)
            intent.putExtra("levelNumber", UserData(this).getLastAvailableLevel())
            startActivity(intent)
        }

        val mapButton: Button = findViewById(R.id.map_button)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("weather", currentWeather)
            startActivity(intent)
        }

        val listOfWeathers = arrayOf("Automatic", Windy(), Hot(), Cold(), Rainy(), Normal())

        val weatherSelector = findViewById<Spinner>(R.id.weatherSpinner)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfWeathers)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        weatherSelector.adapter = arrayAdapter
        weatherSelector.onItemSelectedListener = this

        connection = object : ServiceConnection {
            override fun onServiceDisconnected(componentName: ComponentName) {
                weatherService = null
            }
            override fun onServiceConnected(componentName: ComponentName, service: IBinder) {
                weatherService = (service as WeatherService.WeatherServiceBinder).service
                if(!isCheckingForPermissions) updateCurrentWeather()
            }
        }

        startService(Intent(this, WeatherService::class.java))
        bindService(Intent(this, WeatherService::class.java),connection, Context.BIND_AUTO_CREATE)
    }

    private fun askForForegroundPermissions(){

        isCheckingForPermissions = true

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            isCheckingForPermissions = false

            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)

            if(weatherService != null) updateCurrentWeather()
        }

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,))
    }

    private fun updateCurrentWeather(){

        val playButton : Button = findViewById(R.id.play_button)
        val mapButton : Button = findViewById(R.id.map_button)

        playButton.isClickable = false
        mapButton.isClickable = false
        playButton.alpha = 0.5f
        mapButton.alpha = 0.5f

        CoroutineScope(Dispatchers.IO).launch{

            val currentWeatherDeferred = async { weatherService?.updateWeather() }

            val weatherResponse = currentWeatherDeferred.await()

            currentWeather = if(weatherResponse == null) {
                Snackbar.make(findViewById(R.id.imageView), "No weather information available, using default weather", BaseTransientBottomBar.LENGTH_SHORT).show()
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

    private fun setWeatherIndicator() {
        val weatherIcon : ImageView = findViewById(R.id.weatherIcon)
        val weatherText : TextView = findViewById(R.id.weatherText)
        val progressBar : ProgressBar = findViewById(R.id.progressBarWeather)

        this@MainActivity.runOnUiThread {
            weatherIcon.isVisible = true
            progressBar.isVisible = false
            weatherIcon.setImageResource(resources.getIdentifier(currentWeather.toString().lowercase(), "drawable", this.packageName))
            weatherText.text = currentWeather.toString()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedWeather = parent?.getItemAtPosition(position)
        if(selectedWeather == "Automatic"){

            val weatherIcon : ImageView = findViewById(R.id.weatherIcon)
            val weatherText : TextView = findViewById(R.id.weatherText)
            val progressBar : ProgressBar = findViewById(R.id.progressBarWeather)

            this@MainActivity.runOnUiThread {
                weatherIcon.isVisible = false
                progressBar.isVisible = true
                weatherText.text = "- - -"
            }

            updateCurrentWeather()
        }
        else{
            currentWeather = selectedWeather as WeatherState
            setWeatherIndicator()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}
