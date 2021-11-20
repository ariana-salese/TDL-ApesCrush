package com.example.tptdl

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.tptdl.weatherAPI.Weather
import com.example.tptdl.weatherAPI.WeatherState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.*

class WeatherService : Service() {

    private val binder: IBinder = WeatherServiceBinder()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var currentWeather: WeatherState

    override fun onCreate() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        Toast.makeText(this, "creating service", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun getCurrentWeather() : WeatherState?{
        if(!this::currentWeather.isInitialized) return null
        return currentWeather
    }

    suspend fun updateWeather() : WeatherState?{

        val location = GlobalScope.async{getLocation()}

        if(location.await() == null){
            println("No hay location")
            return null
        }

        println("LOCATION: LAT: " + location.await()!!.latitude + " LON: " + location.await()!!.longitude)

        val weather = Weather()

        currentWeather = weather.fetchCurrent(location.await())

        return currentWeather
    }

    private suspend fun getLocation() : Location? {

        if (!checkForPermissions()) return null

        val lastLocation = GlobalScope.async{getLastLocation()}
        if(lastLocation.await() != null) return lastLocation.await()

        println("Obteniendo current location")

        return withContext(Dispatchers.IO) { getCurrentLocation() }
    }

    private fun getLastLocation() : Location? {

        return try{
            Tasks.await(fusedLocationClient.lastLocation)
            null // Esto hace que no se pueda usar la ubicación guardada en el caché, y siempre se actualize (TODO Luego lo sacamos)
        } catch (e: SecurityException){
            println("No permissions - last location")
            null
        } catch (e: Exception){
            println(e)
            null
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "Unbinding service", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Toast.makeText(this, "service Disconnected", Toast.LENGTH_SHORT).show()
        println("Disconnected on service")
        super.onDestroy()
    }

    private fun getCurrentLocation() : Location? {

        val cancellationSource = CancellationTokenSource()

        return try{
            Tasks.await(fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY,cancellationSource.token))
        } catch (e: SecurityException) {
            println("No permissions - current location")
            null
        } catch (e: Exception) {
            println(e)
            null
        }
    }

    private fun checkForPermissions() : Boolean {
        if (ActivityCompat.checkSelfPermission(
                baseContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                baseContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            print("Location permission not granted")
            return false
        }
        return true
    }

    inner class WeatherServiceBinder : Binder(){
        val service: WeatherService = this@WeatherService
    }
}