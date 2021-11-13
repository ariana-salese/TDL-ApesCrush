package com.example.tptdl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class mapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

    }
    fun clickOnLevelButton(view: View) {
        println(view.id)
    }
    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()
        println("START MAP")
    }

    override fun onResume() {
        super.onResume()
        println("RESUME MAP")
    }

    override fun onPause() {
        super.onPause()
        println("PAUSE MAP")
    }

    override fun onStop() {
        super.onStop()
        println("STOP MAP")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("DESTROY MAP")
    }

    override fun onRestart() {
        super.onRestart()
        println("RESTART MAP")
    }
}

