package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.gamelogic.gameboard.RuleSet

class TokenRandomizer(private val ruleSet: RuleSet) {     // bombAppearanceRate is expressed in percentages (0 to 100)
    private val allTokens : MutableList<Token> = mutableListOf()
    private val bombAppearanceRate = ruleSet.obtainBombRates()
    private val myTokens : MutableList<Token> = mutableListOf() // Void() Token is excluded from this, in fact, it's excluded from this whole class
    private val myFruits : MutableList<Fruit> = mutableListOf()


    init {
        val tokensToAdd = ruleSet.retrieveTokens()
        addTokens(tokensToAdd)
        myFruits.add(Apple())
        myFruits.add(Banana())
        myFruits.add(Grapes())
        myFruits.add(Orange())
        myFruits.add(Strawberry())
        myTokens.add(Apple())
        myTokens.add(Banana())
        myTokens.add(Grapes())
        myTokens.add(Orange())
        myTokens.add(Strawberry())
        myTokens.add(Bomb(ruleSet))
        for (i in 0 until (100 - bombAppearanceRate)) {    // 100 iterations base to allow bombAppearanceRate to have it's proper effect due to it being a percentage
            myFruits.forEach { allTokens.add(it) }
        }
        val amountOfFruitTokens = allTokens.size
        if (bombAppearanceRate == 100) {    // To prevent division by zero
            for (i in 0 until 100) {
                allTokens.add(Bomb(ruleSet))
            }
        }
        else {
            for (i in 0 until ((amountOfFruitTokens * bombAppearanceRate) / (100 - bombAppearanceRate))) {
                allTokens.add(Bomb(ruleSet))
            }
        }
    }

    private fun addTokens(tokensToAdd: MutableList<Token>) {
        tokensToAdd.forEach {
            if (!it.isExplosive()) { // if Token isn't explosive, it's a fruit
                myFruits.add(it as Fruit)
            }
            myTokens.add(it)
        }
    }

    fun randomToken() : Token { // Affected by bombAppearanceRate
        return allTokens.elementAt((0 until allTokens.size).random())
    }

    fun randomFruit() : Fruit {
        return myFruits.elementAt((0 until myFruits.size).random())
    }
}