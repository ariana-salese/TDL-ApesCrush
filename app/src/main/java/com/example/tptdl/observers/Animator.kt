package com.example.tptdl.observers

import android.widget.TableRow
import com.example.tptdl.LevelActivity
import com.example.tptdl.weatherAPI.Weather
import com.example.tptdl.weatherAPI.WeatherState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class Animator(val context : LevelActivity) : Observer { //TODO mandar a volar

    override fun update(o: Observable?, arg: Any?) {
        val weather = o as WeatherState
        GlobalScope.launch { weather.starAnimation(context) }
    }
}