package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.gamelogic.gameboard.RuleSet

class TokenRandomizer(private val ruleSet: RuleSet) {     // bombAppearanceRate is expressed in percentages (0 to 100)
    private val allTokens : MutableList<Token> = mutableListOf()
    private val allFruits : MutableList<Fruit> = mutableListOf()
    private val numberOfTokens : Int = 6    // Void() Token is excluded from this, in fact, it's excluded from this whole class
    private val numberOfFruits : Int = 5
    private val bombAppearanceRate = ruleSet.obtainBombRates()


    init {
        for (i in 0 until (100 - bombAppearanceRate)) {    // 100 iterations base to allow bombAppearanceRate to have it's proper effect due to it being a percentage
            allTokens.add(Apple())
            allTokens.add(Banana())
            allTokens.add(Grapes())
            allTokens.add(Orange())
            allTokens.add(Strawberry())
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
        allFruits.add(Apple())
        allFruits.add(Banana())
        allFruits.add(Grapes())
        allFruits.add(Orange())
        allFruits.add(Strawberry())
    }

    fun randomToken() : Token {
        return allTokens.elementAt((0 until allTokens.size).random())
    }

    fun randomFruit() : Fruit {
        return allFruits.elementAt((0 until numberOfFruits).random())
    }
}