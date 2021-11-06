package com.example.tptdl.gamelogic.tokens

class TokenRandomizer {
    private val allTokens : MutableList<Token> = mutableListOf()
    private val allFruits : MutableList<Fruit> = mutableListOf()
    private val numberOfTokens : Int = 6
    private val numberOfFruits : Int = 5
    private val bombAppearanceRate : Int = 5    // expressed in percentages (0 to 100)

    init {
        for (i in 0 until (100 - bombAppearanceRate)) {    // 100 iterations base to allow bombAppearanceRate to have it's proper effect
            allTokens.add(Apple())
            allTokens.add(Banana())
            allTokens.add(Grapes())
            allTokens.add(Peach())
            allTokens.add(Strawberry())
        }
        for (i in 0 until bombAppearanceRate) { allTokens.add(Bomb()) }
        allFruits.add(Apple())
        allFruits.add(Banana())
        allFruits.add(Grapes())
        allFruits.add(Peach())
        allFruits.add(Strawberry())
    }

    fun randomToken() : Token {
        return allTokens.elementAt((0 until ((numberOfFruits * (100 - bombAppearanceRate)) + (numberOfTokens - numberOfFruits) * bombAppearanceRate)).random())
    }

    fun randomFruit() : Fruit {
        return allFruits.elementAt((0 until numberOfFruits).random())
    }
}