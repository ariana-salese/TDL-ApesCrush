package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.R
import java.nio.file.Path

class Banana : Fruit {

    override val pointValue = 25

    override fun toString() : String {
        return "Banana"
    }

    override fun getPath() : Int {
        return R.drawable.banana
    }

}
