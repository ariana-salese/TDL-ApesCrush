package com.example.tptdl.gamelogic.gameboard

interface RuleSet {
    // Returns the percentage chance (as an Int) of a bomb appearing in a determined cell
    fun obtainBombRates(): Int

}
