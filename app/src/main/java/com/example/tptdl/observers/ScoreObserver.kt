package com.example.tptdl.observers

import android.widget.ProgressBar
import android.widget.TextView
import com.example.tptdl.LevelActivity
import com.example.tptdl.R
import java.util.*

class ScoreObserver(val context : LevelActivity) : Observer {
    private var bar = context.findViewById<ProgressBar>(R.id.scoreProgressBar)
    private var winThresholdScore = context.findViewById<TextView>(R.id.winThresholdScore)
    private var currentScore = context.findViewById<TextView>(R.id.currentScore)

    init {
        bar.progress = 0
    }

    override fun update(o: Observable?, arg: Any?) {
        bar.progress = arg as Int
        currentScore.text = arg.toString()
    }

    fun setMax(winThreshold: Int) {
        bar.max = winThreshold
        winThresholdScore.text = winThreshold.toString()
    }
}