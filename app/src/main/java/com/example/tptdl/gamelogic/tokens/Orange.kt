package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.R

class Orange : Fruit() {
    override val pointValue = 15

    override fun toString() : String {
        return "Orange"
    }
    override fun getPath() : Int {
        return R.drawable.orange
    }
}
