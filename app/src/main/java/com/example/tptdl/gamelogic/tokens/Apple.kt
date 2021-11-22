package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.R

class Apple : Fruit() {
    override val pointValue = 20

    override fun toString() : String {
        return "Apple"
    }
    override fun getPath() : Int {
        return R.drawable.apple
    }
}
