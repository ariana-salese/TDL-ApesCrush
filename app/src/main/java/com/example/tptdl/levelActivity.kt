package com.example.tptdl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class levelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
    }

    fun clickOnLevelButton(view: View) {
        println(view.id)
    }
}