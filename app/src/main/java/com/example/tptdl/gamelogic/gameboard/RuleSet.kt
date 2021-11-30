package com.example.tptdl.gamelogic.gameboard

import com.example.tptdl.gamelogic.tokens.Token

interface RuleSet {
    // Returns the percentage chance (as an Int) of a bomb appearing in a determined cell
    fun obtainBombRates(): Int

    fun obtainExplosionRadius(): Int

    fun getBombPath() : Int

    fun weatherEvent(gameBoard: GameBoard)

    fun retrieveTokens() : MutableList<Token>
}
