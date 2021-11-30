package com.example.tptdl.observers


import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.tptdl.LevelActivity
import com.example.tptdl.R
import java.util.*

class ProgressBarObserver(context : LevelActivity) : Observer{
    private var bar = context.findViewById<ProgressBar>(R.id.scoreProgressBar)

    init {
        bar.progress = 0

    }

    override fun update(o: Observable?, arg: Any?) {
        bar.progress = arg as Int
    }

    fun setMax(winThreshold: Int) {
        bar.max = winThreshold
    }


}