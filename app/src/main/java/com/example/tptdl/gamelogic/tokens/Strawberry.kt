package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.R

class Strawberry : Fruit() {
    override val pointValue = 10

    override fun toString() : String {
        return "Strawberry"
    }
    override fun getPath() : Int {
        return R.drawable.strawberry
    }
}
