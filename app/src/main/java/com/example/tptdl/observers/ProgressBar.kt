package com.example.tptdl.observers


import android.widget.ProgressBar
import java.util.*

class ProgressBar(winThreshold : Int, progressBar : ProgressBar) : Observer{
    private var bar = progressBar

    init {
        bar.max = winThreshold
        bar.progress = 0
    }

    override fun update(o: Observable?, arg: Any?) {
        bar.progress = arg as Int
    }


}