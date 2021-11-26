package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.R

class Grapes() : Fruit() {

    override val pointValue = 10

    override fun toString() : String {
        return "Grapes"
    }
    override fun getPath() : Int {
        return R.drawable.grapes
    }
}
